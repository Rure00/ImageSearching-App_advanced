package com.project.imagesearchingadvancedapplication.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.project.imagesearchingadvancedapplication.data.ImageData
import com.project.imagesearchingadvancedapplication.viewmodel.model.api.RetrofitController


class MainViewModel(): ViewModel() {
    //https://stackoverflow.com/questions/67810019/difference-between-by-viewmodels-and-viewmodel-creation-using-factory
    private val retrofitController = RetrofitController()

    val likedImagesLiveData = MutableLiveData(mutableListOf<ImageData>())

    suspend fun getImages(query: String): List<ImageData>
        = retrofitController.getImages(query)

    fun getVideo(query: String) {

    }
    fun getLastQuery(activity: Activity): String {
        return ""
    }
    fun saveLastQuery(activity: Activity, query: String) {

    }
}