package com.project.imagesearchingadvancedapplication.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.project.imagesearchingadvancedapplication.presentation.viewmodel.MainViewModel

class MainViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel() as T
        }

        throw IllegalArgumentException("MainViewModelFactory: Unknown ViewModel Class")
    }
}