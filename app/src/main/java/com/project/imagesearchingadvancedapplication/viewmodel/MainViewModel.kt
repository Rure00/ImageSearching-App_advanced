package com.project.imagesearchingadvancedapplication.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.imagesearchingadvancedapplication.data.ImageData
import com.project.imagesearchingadvancedapplication.viewmodel.model.api.RetrofitController


class MainViewModel(): ViewModel() {
    //https://stackoverflow.com/questions/67810019/difference-between-by-viewmodels-and-viewmodel-creation-using-factory
    private val retrofitController = RetrofitController()

    val likedImagesLiveData = MutableLiveData(mutableListOf<ImageData>())

    fun getImages(query: String) {

    }
    fun getVideo(query: String) {

    }
    fun getLastQuery(activity: Activity) {

    }
    fun saveLastQuery(activity: Activity) {

    }
}