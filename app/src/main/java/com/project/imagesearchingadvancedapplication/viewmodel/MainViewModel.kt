package com.project.imagesearchingadvancedapplication.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.imagesearchingadvancedapplication.viewmodel.model.SharedPreferenceUtils
import com.project.imagesearchingadvancedapplication.viewmodel.model.api.RetrofitController
import kotlinx.coroutines.launch

class MainViewModel(val number: Int): ViewModel() {
    //https://stackoverflow.com/questions/67810019/difference-between-by-viewmodels-and-viewmodel-creation-using-factory
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