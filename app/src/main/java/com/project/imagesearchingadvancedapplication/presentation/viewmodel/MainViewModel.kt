package com.project.imagesearchingadvancedapplication.presentation.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import com.project.imagesearchingadvancedapplication.domain.use_case.GetImageDataUseCase
import com.project.imagesearchingadvancedapplication.domain.use_case.GetLastQueryUseCase
import com.project.imagesearchingadvancedapplication.domain.use_case.SaveQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImageDataUseCase: GetImageDataUseCase,
    private val getLastQueryUseCase: GetLastQueryUseCase,
    private val saveQueryUseCase: SaveQueryUseCase,
): ViewModel() {
    //https://stackoverflow.com/questions/67810019/difference-between-by-viewmodels-and-viewmodel-creation-using-factory

    val likedImagesLiveData = MutableLiveData(mutableListOf<ImageData>())

    suspend fun getImageData(query: String): List<ImageData>
        = getImageDataUseCase.invoke(query)

    fun getLastQuery(): String
        = getLastQueryUseCase.invoke()

    fun saveLastQuery(query: String) {
        saveQueryUseCase.invoke(query)
    }
}