package com.project.imagesearchingadvancedapplication.data.entity

data class VideoSearchEntity(
    val meta: Meta,
    val documents: List<Document>
) {
    data class Meta(
        val total_count: Int,
        val pageable_count: Int,
        val is_end: Boolean
    )
    data class Document(
        val title: String,
        val url: String,
        val datetime: String,
        val play_time: Int,
        val thumbnail: String,
        val author: String
    )
}
