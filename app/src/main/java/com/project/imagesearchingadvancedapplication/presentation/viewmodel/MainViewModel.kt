package com.project.imagesearchingadvancedapplication.presentation.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.imagesearchingadvancedapplication.addAll
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import com.project.imagesearchingadvancedapplication.domain.use_case.GetImageDataUseCase
import com.project.imagesearchingadvancedapplication.domain.use_case.GetImageFromLocalUseCase
import com.project.imagesearchingadvancedapplication.domain.use_case.GetLastQueryUseCase
import com.project.imagesearchingadvancedapplication.domain.use_case.SaveImageUseCase
import com.project.imagesearchingadvancedapplication.domain.use_case.SaveQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImageDataUseCase: GetImageDataUseCase,
    private val getLastQueryUseCase: GetLastQueryUseCase,
    private val saveQueryUseCase: SaveQueryUseCase,
    private val saveImageUseCase: SaveImageUseCase,
    private val getImageFromLocalUseCase: GetImageFromLocalUseCase
): ViewModel() {
    //https://stackoverflow.com/questions/67810019/difference-between-by-viewmodels-and-viewmodel-creation-using-factory

    val likedImagesLiveData = MutableLiveData(mutableListOf<ImageData>())

    suspend fun getImageData(query: String, page: Int): List<ImageData>
        = getImageDataUseCase.invoke(query, page)

    fun getLastQuery(): String
        = getLastQueryUseCase.invoke()

    fun saveLastQuery(query: String) {
        saveQueryUseCase.invoke(query)
    }

    fun saveImages() {
        likedImagesLiveData.value?.let {
            saveImageUseCase.invoke(it)
        }
    }
    fun getImagesFromLocal() {
        val result = getImageFromLocalUseCase.invoke()
        likedImagesLiveData.addAll(result)
    }
}