package com.lowbottgames.reader.reddit.kotlin.repository

import com.lowbottgames.reader.reddit.kotlin.database.KNDatabase
import com.lowbottgames.reader.reddit.kotlin.database.FeedEntity
import com.lowbottgames.reader.reddit.kotlin.model.Feed
import com.lowbottgames.reader.reddit.kotlin.network.ApiEndpoint

class FeedRepositoryImpl(
    private val database: KNDatabase,
    private val service: ApiEndpoint
) : FeedRepository {

    private var feedCache: List<Feed>? = null

    override suspend fun feed(name: String, isRefresh: Boolean): List<Feed>? {
        var feed: List<Feed>? = null

        if (!isRefresh) {
            feed = feedCache
        }

        if (feed == null && !isRefresh) {
            val feedEntity = database.databaseDao.getAll()
            feed = feedEntity.map { item ->
                Feed(
                    item.title,
                    item.author,
                    item.thumbnail,
                    item.text,
                    item.url
                )
            }
            feedCache = feed
        }

        if (feed == null || feed.isEmpty()) {
            val feedResponse = service.feed(name)
            feed = feedResponse.data?.children?.mapNotNull {
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
            feedCache = feed
        }
        return feed
    }

}