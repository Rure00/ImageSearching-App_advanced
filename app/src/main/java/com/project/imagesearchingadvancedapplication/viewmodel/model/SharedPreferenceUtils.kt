package com.project.imagesearchingadvancedapplication.viewmodel.model

import android.app.Activity
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.imagesearchingadvancedapplication.data.ImageData


class SharedPreferenceUtils(activity: Activity) {

    companion object {
        private const val APP_SHARED_PREFS = "image_search"
        const val LIKED_IMAGES_TAG = "liked_image_tag"
        const val LAST_QUERY = "last_query"
    }

    private val sharedPreferences: SharedPreferences =
        activity.getPreferences(Activity.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun saveQuery(query: String) {
        editor.putString(LAST_QUERY, query).commit()
    }
    fun getLastQuery() = sharedPreferences.getString(LAST_QUERY, "")

    fun saveImages(imageList: List<ImageData>) {
        val gson = Gson()
        val json = gson.toJson(imageList)
        editor.putString(LIKED_IMAGES_TAG, json).commit()
    }
    fun getImages(): List<ImageData> {
        val gson = Gson()
        val json = sharedPreferences.getString(LIKED_IMAGES_TAG, "")

        return if(json?.isNotBlank() == true) {
            gson.fromJson(
                json,
                object : TypeToken<List<ImageData?>?>() {}.type
            )
        } else mutableListOf()
    }
}