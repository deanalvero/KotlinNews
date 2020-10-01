package com.lowbottgames.reader.reddit.kotlin

import com.lowbottgames.reader.reddit.kotlin.network.ApiEndpoint
import com.lowbottgames.reader.reddit.kotlin.network.ServiceBuilder
import org.junit.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@Ignore("This is just for testing a live api call.")
class LiveApiCallTest {

    private lateinit var testee: ApiEndpoint

    @Before
    fun setUp() {
        testee = ServiceBuilder.buildService(ApiEndpoint::class.java)
    }

    @Test
    fun kotlinFeed_liveApiCall() = runBlocking {
        val response = testee.kotlinFeed()
        assertNotNull(response)

        val feedData = response.data
        assertNotNull(feedData)

        val feedDataChildren = feedData?.children
        assertNotNull(feedDataChildren)
        assertNotEquals(0, feedDataChildren?.size)
    }

}