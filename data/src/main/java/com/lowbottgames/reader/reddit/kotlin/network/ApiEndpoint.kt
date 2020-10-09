package com.lowbottgames.reader.reddit.kotlin.network

import com.lowbottgames.reader.reddit.kotlin.network.model.FeedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndpoint {

    @GET("/r/{subreddit}/.json")
    suspend fun feed(@Path("subreddit") subreddit: String) : FeedResponse

}