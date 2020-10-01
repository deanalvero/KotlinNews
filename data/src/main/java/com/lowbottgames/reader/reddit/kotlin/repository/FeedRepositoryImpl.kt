package com.lowbottgames.reader.reddit.kotlin.repository

import com.lowbottgames.reader.reddit.kotlin.database.KNDatabase
import com.lowbottgames.reader.reddit.kotlin.database.FeedEntity
import com.lowbottgames.reader.reddit.kotlin.model.Feed
import com.lowbottgames.reader.reddit.kotlin.network.ApiEndpoint

class FeedRepositoryImpl(
    private val database: KNDatabase,
    private val service: ApiEndpoint
) : FeedRepository {

    private var kotlinFeedCache: List<Feed>? = null

    override suspend fun kotlinFeed(isRefresh: Boolean): List<Feed>? {
        var kotlinFeed: List<Feed>? = null

        if (!isRefresh) {
            kotlinFeed = kotlinFeedCache
        }

        if (kotlinFeed == null && !isRefresh) {
            val feedEntity = database.databaseDao.getAll()
            kotlinFeed = feedEntity.map { item ->
                Feed(
                    item.title,
                    item.author,
                    item.thumbnail,
                    item.text,
                    item.url
                )
            }
            kotlinFeedCache = kotlinFeed
        }

        if (kotlinFeed == null || kotlinFeed.isEmpty()) {
            val feedResponse = service.kotlinFeed()
            kotlinFeed = feedResponse.data?.children?.mapNotNull {
                val item = it.data
                if (item != null) {
                    database.databaseDao.insert(
                        FeedEntity(
                            item.id ?: "",
                            item.title,
                            item.author,
                            item.thumbnail,
                            item.text,
                            item.url
                        )
                    )

                    Feed(
                        item.title,
                        item.author,
                        item.thumbnail,
                        item.text,
                        item.url
                    )
                } else {
                    null
                }
            }
            kotlinFeedCache = kotlinFeed
        }
        return kotlinFeed
    }

}