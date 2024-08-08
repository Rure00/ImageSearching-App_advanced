package com.project.imagesearchingadvancedapplication.data.source.retrofit

import com.project.imagesearchingadvancedapplication.data.source.retrofit.dto.ImageSearchResponse
import com.project.imagesearchingadvancedapplication.data.api.dto.VideoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/image")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int = 30,
    ): ImageSearchResponse

    @GET("search/vclip")
    suspend fun getVideos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int = 30,
    ): VideoSearchResponse
}