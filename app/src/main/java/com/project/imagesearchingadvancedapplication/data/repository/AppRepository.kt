package com.project.imagesearchingadvancedapplication.data.repository

import android.provider.ContactsContract.CommonDataKinds.Im
import com.project.imagesearchingadvancedapplication.domain.model.ImageData

interface AppRepository {
    suspend fun getImageData(query: String, size: Int): List<ImageData>
    fun saveImageData(list: List<ImageData>)
    fun getLastQuery(): String
    fun saveLastQuery(query: String)
}