package com.project.imagesearchingadvancedapplication.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.project.imagesearchingadvancedapplication.viewmodel.model.SharedPreferenceUtils
import com.project.imagesearchingadvancedapplication.viewmodel.model.api.RetrofitController

class MainViewModel: ViewModel() {
    private val retrofitController = RetrofitController()

    fun getImages(query: String) {

    }
    fun getVideo(query: String) {

    }
    fun getLastQuery(activity: Activity) {

    }
    fun saveLastQuery(activity: Activity) {

    }
}