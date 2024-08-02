package com.project.imagesearchingadvancedapplication.viewmodel.model.api

import com.google.gson.GsonBuilder
import com.project.imagesearchingadvancedapplication.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    private const val BASE_URL = "https://dapi.kakao.com/v2/"
    private const val CONNECT_TIMEOUT_SEC = 20000L

    private const val KAKAO_APP_KEY = BuildConfig.KAKAO_API_KEY
    private const val KAKAO_REST_API_KEY = BuildConfig.KAKAO_REST_KEY

    fun getInstance() : Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return instance!!
    }

    private fun getOkhttpClient()
        = OkHttpClient()
            .newBuilder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .header("Authorization", "KakaoAK $KAKAO_REST_API_KEY")
            val request = requestBuilder.build()

            chain.proceed(request)
        }.build()
}