package com.project.imagesearchingadvancedapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageData(
    val imageUrl: String,
    val from: String,
    val time: String
): Parcelable
