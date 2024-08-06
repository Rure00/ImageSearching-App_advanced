package com.project.imagesearchingadvancedapplication.viewmodel.model.api

import com.project.imagesearchingadvancedapplication.data.ImageData


class RetrofitController {
    private val client = RetrofitClient.getInstance().create(RetrofitService::class.java)

    suspend fun getImages(query: String): List<ImageData>
        = client.getImages(query).documents.map {
            ImageData(
                imageUrl = it.image_url,
                from = it.display_sitename,
                time = it.datetime,
                category = ImageData.Category.Image
            )
        }

    suspend fun getVideos(query: String): List<ImageData>
            = client.getVideos(query).documents.map {
        ImageData(
            imageUrl = it.thumbnail,
            from = "[${it.author}] ${it.title}",
            time = it.datetime,
            category = ImageData.Category.Video
        )
    }
}