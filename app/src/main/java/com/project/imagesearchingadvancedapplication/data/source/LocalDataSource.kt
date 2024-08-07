package com.project.imagesearchingadvancedapplication.data.source

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(@ActivityContext context: Context) {
    companion object {
        private const val APP_SHARED_PREFS = "image_search"
        const val LIKED_IMAGES_TAG = "liked_image_tag"
        const val LAST_QUERY = "last_query"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


}