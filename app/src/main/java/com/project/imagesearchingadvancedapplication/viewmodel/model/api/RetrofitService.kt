package com.project.imagesearchingapp.model.api

import com.project.imagesearchingadvancedapplication.viewmodel.model.api.ImageSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/image")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("size") size: Int = 80,
        @Query("page") page: Int = 1
    ): ImageSearchResponse
}