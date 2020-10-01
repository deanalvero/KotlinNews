package com.lowbottgames.reader.reddit.kotlin.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_table")
data class FeedEntity(

    @PrimaryKey
    val id: String,

    val title: String?,
    val author: String?,
    val thumbnail: String?,
    val text: String?,
    val url: String?

)