package com.lowbottgames.reader.reddit.kotlin

import android.app.Application
import com.lowbottgames.reader.reddit.kotlin.database.KNDatabase
import com.lowbottgames.reader.reddit.kotlin.network.ApiEndpoint
import com.lowbottgames.reader.reddit.kotlin.network.ServiceBuilder
import com.lowbottgames.reader.reddit.kotlin.repository.FeedRepository
import com.lowbottgames.reader.reddit.kotlin.repository.FeedRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KotlinNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single { KNDatabase.getInstance(get()) }
            single { ServiceBuilder.buildService(ApiEndpoint::class.java) }
            single<FeedRepository> { FeedRepositoryImpl(get(), get()) }
        }

        startKoin {
            androidLogger()
            androidContext(this@KotlinNewsApplication)
            modules(appModule)
        }
    }
}