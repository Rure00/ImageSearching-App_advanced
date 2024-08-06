package com.project.imagesearchingadvancedapplication.fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.imagesearchingadvancedapplication.MainActivity
import com.project.imagesearchingadvancedapplication.add
import com.project.imagesearchingadvancedapplication.addAll
import com.project.imagesearchingadvancedapplication.data.ImageData
import com.project.imagesearchingadvancedapplication.databinding.FragmentSearchingBinding
import com.project.imagesearchingadvancedapplication.viewmodel.model.SharedPreferenceUtils
import com.project.imagesearchingadvancedapplication.viewmodel.model.api.RetrofitController
import com.project.imagesearchingadvancedapplication.recycler_view.ImageRvAdapter
import com.project.imagesearchingadvancedapplication.remove
import com.project.imagesearchingadvancedapplication.viewmodel.MainViewModel
import com.project.imagesearchingadvancedapplication.viewmodel.factory.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchingFragment : Fragment() {
    private var _binding: FragmentSearchingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), MainViewModelFactory())[MainViewModel::class.java]
    }

    private val imageList = mutableListOf<ImageData>()
    private val imageRvAdapter = ImageRvAdapter(object: ImageRvAdapter.ClickListener {
        override fun onImageClick(imageData: ImageData) {
            if(imageData.isLiked) {
                viewModel.likedImagesLiveData.remove(imageData)
            } else {
                viewModel.likedImagesLiveData.add(imageData)
            }
            imageData.isLiked = !imageData.isLiked

            Log.d("SearchingFragment", "On Touch: image size: ${imageList.filter { it.isLiked }.size}")
            Log.d("Like", "insert size: ${viewModel.likedImagesLiveData.value!!.size}")
        }
    })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2) //StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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

        viewModel.likedImagesLiveData.observe(viewLifecycleOwner) {
            Log.d("SearchFragment", "LiveData is changed: ${viewModel.likedImagesLiveData.value!!.size}")
        }

        val lastQuery = viewModel.getLastQuery(requireActivity())
        binding.searchEditText.setText(lastQuery)
        if (lastQuery.isNotBlank() && imageList.isEmpty()) startSearch(lastQuery)

        binding.floatingBtn.setOnClickListener {
            binding.imageRecyclerView.smoothScrollToPosition(0)
        }

        binding.searchEditText.setOnKeyListener { _, keyCode, event ->
            if((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)

                val query = binding.searchEditText.text.toString()
                startSearch(query)
            }

            true
        }
        binding.searchBtn.setOnClickListener {
            val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)

            val query = binding.searchEditText.text.toString()
            startSearch(query)
        }
    }



    private fun startSearch(query: String) {
        viewModel.saveLastQuery(requireActivity(), query)

        Log.d("searchingFragment", "search. exist: ${imageList.size}")

        CoroutineScope(Dispatchers.IO).launch {
            val imageResult = viewModel.getImages(query)
            val videoResult = viewModel.getVideos(query)

            val sum = (imageResult + videoResult).sortedByDescending {
                it.time
            }

            imageList.clear()
            imageList.addAll(sum)

            withContext(Dispatchers.Main) {
                imageRvAdapter.submitList(imageList)
            }
        }
    }
}