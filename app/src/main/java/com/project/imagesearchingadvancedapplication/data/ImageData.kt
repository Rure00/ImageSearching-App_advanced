package com.project.imagesearchingadvancedapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageData(
    val imageUrl: String,
    val from: String,
    val time: String,
    val category: Category,
    var isLiked: Boolean = false
): Parcelable {
    enum class Category(index: Int) {
        None(-1), Image(0), Video(1),
    }
}
