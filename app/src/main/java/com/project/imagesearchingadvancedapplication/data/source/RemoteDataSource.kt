package com.project.imagesearchingadvancedapplication.data.source

import com.project.imagesearchingadvancedapplication.data.source.retrofit.RetrofitService
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
     private val client: RetrofitService
) {
    suspend fun getImages(query: String, page: Int): List<ImageData>
            = client.getImages(query, page).documents.map {
        val time = it.datetime.split(".")[0].split("T")
            .joinToString(" ")

        ImageData(
            imageUrl = it.image_url,
            from = it.display_sitename,
            time = time,
            category = ImageData.Category.Image
        )
    }

    suspend fun getVideos(query: String, page: Int): List<ImageData>
            = client.getVideos(query, page).documents.map {
        val time = it.datetime.split(".")[0].split("T")
            .joinToString(" ")


        ImageData(
            imageUrl = it.thumbnail,
            from = "[${it.author}] ${it.title}",
            time = time,
            category = ImageData.Category.Video
        )
    }
}