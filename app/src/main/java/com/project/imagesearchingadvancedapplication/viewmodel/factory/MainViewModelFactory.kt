package com.project.imagesearchingadvancedapplication.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.project.imagesearchingadvancedapplication.viewmodel.MainViewModel

class MainViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(3) as T
        }

        throw IllegalArgumentException("MainViewModelFactory: Unknown ViewModel Class")
    }
}