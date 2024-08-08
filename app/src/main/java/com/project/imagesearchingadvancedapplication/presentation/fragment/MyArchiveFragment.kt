package com.project.imagesearchingadvancedapplication.presentation.fragment

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import com.project.imagesearchingadvancedapplication.databinding.FragmentMyArchiveBinding
import com.project.imagesearchingadvancedapplication.presentation.recycler_view.ImageRvAdapter
import com.project.imagesearchingadvancedapplication.remove
import com.project.imagesearchingadvancedapplication.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyArchiveFragment : Fragment() {
    private var _binding: FragmentMyArchiveBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val imageRvAdapter by lazy {
        ImageRvAdapter(object: ImageRvAdapter.ClickListener {
                override fun onImageClick(imageData: ImageData) {
                    imageData.isLiked = false
                    viewModel.likedImagesLiveData.remove(imageData)

                    Log.d("Like", "remove size: ${viewModel.likedImagesLiveData.value!!.size}")
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyArchiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.likedImagesLiveData.observe(viewLifecycleOwner) {
            imageRvAdapter.submitList(it.toList())
            Log.d("MyArchiveFragment", "LiveData is changed: ${viewModel.likedImagesLiveData.value?.size}")
        }


        binding.archiveRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = imageRvAdapter.apply {
                submitList(viewModel.likedImagesLiveData.value)
            }
            addItemDecoration(object: ItemDecoration() {
                val px = 10
                val spanCount = 2

                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    val index = parent.getChildLayoutPosition(view)
                    val isLeft = (index % spanCount == 0)
                    outRect.set(
                        if (isLeft) px else px/2,
                        0,
                        if (isLeft) px/2 else px,
                        px
                    )
                }
            })
        }
    }
}