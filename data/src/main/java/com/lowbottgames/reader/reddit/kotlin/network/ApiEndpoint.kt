package com.lowbottgames.reader.reddit.kotlin.network

import com.lowbottgames.reader.reddit.kotlin.network.model.FeedResponse
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("/r/kotlin/.json")
    suspend fun kotlinFeed() : FeedResponse

}