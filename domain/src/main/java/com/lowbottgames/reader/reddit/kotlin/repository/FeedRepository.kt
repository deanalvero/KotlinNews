package com.lowbottgames.reader.reddit.kotlin.repository

import com.lowbottgames.reader.reddit.kotlin.model.Feed

interface FeedRepository {

    suspend fun kotlinFeed(isRefresh: Boolean) : List<Feed>?

}