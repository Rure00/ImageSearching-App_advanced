package com.project.imagesearchingadvancedapplication.fragment

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.project.imagesearchingadvancedapplication.MainActivity
import com.project.imagesearchingadvancedapplication.databinding.FragmentMyArchiveBinding
import com.project.imagesearchingadvancedapplication.recycler_view.ImageRvAdapter

class MyArchiveFragment : Fragment() {
    private var _binding: FragmentMyArchiveBinding? = null
    private val binding get() = _binding!!

    private val mainActivity by lazy {
        requireActivity() as MainActivity
    }
    private lateinit var imageRvAdapter: ImageRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyArchiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Viewmodel 생성 후 LiveData 로 추가
//        imageRvAdapter = ImageRvAdapter(mainActivity.likedImages, true) {
//            val index = mainActivity.likedImages.indexOf(it)
//            mainActivity.likedImages.removeAt(index)
//            imageRvAdapter.notifyItemRemoved(index)
//        }

        binding.archiveRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = imageRvAdapter
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