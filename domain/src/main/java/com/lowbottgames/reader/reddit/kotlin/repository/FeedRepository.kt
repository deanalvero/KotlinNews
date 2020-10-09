package com.lowbottgames.reader.reddit.kotlin.repository

import com.lowbottgames.reader.reddit.kotlin.model.Feed

interface FeedRepository {

    suspend fun feed(name: String, isRefresh: Boolean) : List<Feed>?

}