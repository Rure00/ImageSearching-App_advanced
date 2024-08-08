package com.project.imagesearchingadvancedapplication.data.repository

import android.provider.ContactsContract.CommonDataKinds.Im
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import dagger.Provides

interface AppRepository {
    suspend fun getImageData(query: String, page: Int): List<ImageData>
    fun saveImageData(list: List<ImageData>)
    fun getLastQuery(): String
    fun saveLastQuery(query: String)
    fun getImageFromLocal(): List<ImageData>
}