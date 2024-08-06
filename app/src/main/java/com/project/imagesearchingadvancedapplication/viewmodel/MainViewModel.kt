package com.project.imagesearchingadvancedapplication.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.project.imagesearchingadvancedapplication.data.ImageData
import com.project.imagesearchingadvancedapplication.viewmodel.model.SharedPreferenceUtils
import com.project.imagesearchingadvancedapplication.viewmodel.model.api.RetrofitController


class MainViewModel(): ViewModel() {
    //https://stackoverflow.com/questions/67810019/difference-between-by-viewmodels-and-viewmodel-creation-using-factory
    private val retrofitController = RetrofitController()

    val likedImagesLiveData = MutableLiveData(mutableListOf<ImageData>())

    //TODO: UseCase 만들고 적용해보기
    suspend fun getImages(query: String): List<ImageData>
        = retrofitController.getImages(query)

    suspend fun getVideos(query: String): List<ImageData>
        = retrofitController.getVideos(query)

    fun getLastQuery(activity: Activity): String {
        val pref = SharedPreferenceUtils(activity)
        return pref.getLastQuery() ?: ""
    }
    fun saveLastQuery(activity: Activity, query: String) {
        val pref = SharedPreferenceUtils(activity)
        pref.saveQuery(query)
    }
}