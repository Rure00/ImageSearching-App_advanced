package com.project.imagesearchingadvancedapplication.fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.project.imagesearchingadvancedapplication.MainActivity
import com.project.imagesearchingadvancedapplication.data.ImageData
import com.project.imagesearchingadvancedapplication.databinding.FragmentSearchingBinding
import com.project.imagesearchingadvancedapplication.viewmodel.model.SharedPreferenceUtils
import com.project.imagesearchingapp.model.api.RetrofitController
import com.project.imagesearchingadvancedapplication.recycler_view.ImageRvAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchingFragment : Fragment() {
    private var _binding: FragmentSearchingBinding? = null
    private val binding get() = _binding!!

    private val retrofitController = RetrofitController()
    private val preferencesUtils by lazy {
        SharedPreferenceUtils(requireActivity())
    }

    private val mainActivity by lazy {
        requireActivity() as MainActivity
    }
    private val imageList = mutableListOf<ImageData>()
    private val imageRvAdapter = ImageRvAdapter(imageList, false) {
        //TODO: change after adding liveData to viewmodel
//        if(mainActivity.likedImages.contains(it)) {
//            mainActivity.likedImages.remove(it)
//        } else {
//            mainActivity.likedImages.add(it)
//        }
//
//        Log.d("Like", "insert size: ${mainActivity.likedImages.size}")
    }

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

        val lastQuery = preferencesUtils.getLastQuery() ?: ""
        binding.searchEditText.setText(lastQuery)
        if (lastQuery.isNotBlank()) startSearch(lastQuery)


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
//        preferencesUtils.saveQuery(query)
//        CoroutineScope(Dispatchers.IO).launch {
//            val result = retrofitController.getImages(query)
//            imageList.clear()
//            imageList.addAll(result)
//
//            withContext(Dispatchers.Main) {
//                imageRvAdapter.notifyItemRangeInserted(0, result.size)
//            }
//        }
    }
}