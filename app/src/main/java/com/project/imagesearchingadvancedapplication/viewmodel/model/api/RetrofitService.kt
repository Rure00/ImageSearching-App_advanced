package com.project.imagesearchingadvancedapplication.viewmodel.model.api

import com.project.imagesearchingadvancedapplication.viewmodel.model.api.dto.ImageSearchResponse
import com.project.imagesearchingadvancedapplication.viewmodel.model.api.dto.VideoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/image")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("size") size: Int = 80,
        @Query("page") page: Int = 1
    ): ImageSearchResponse

    @GET("search/vclip")
    suspend fun getVideos(
        @Query("query") query: String,
        @Query("size") size: Int = 15,
        @Query("page") page: Int = 1
    ): VideoSearchResponse
}