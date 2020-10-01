package com.lowbottgames.reader.reddit.kotlin.network.model

import com.google.gson.annotations.SerializedName

data class FeedDataChildData(
    val id: String?,
    val title: String?,
    val author: String?,
    val thumbnail: String?,

    @SerializedName("selftext")
    val text: String?,
    val url: String?
)