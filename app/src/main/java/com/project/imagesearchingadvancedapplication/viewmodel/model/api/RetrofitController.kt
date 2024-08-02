package com.project.imagesearchingadvancedapplication.viewmodel.model.api

import com.project.imagesearchingadvancedapplication.data.ImageData
import com.project.imagesearchingapp.model.api.RetrofitService


class RetrofitController {
    private val client = RetrofitClient.getInstance().create(RetrofitService::class.java)

    suspend fun getImages(query: String): List<ImageData>
        = client.getImages(query).documents.map {
            ImageData(
                imageUrl = it.image_url,
                from = it.display_sitename,
                time = it.datetime
            )
        }
}