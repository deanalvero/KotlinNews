package com.lowbottgames.reader.reddit.kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FeedEntity::class], version = 1, exportSchema = false)
abstract class KNDatabase : RoomDatabase() {

    abstract val databaseDao: DatabaseDao

    companion object {

        @Volatile
        private lateinit var INSTANCE: KNDatabase

        fun getInstance(context: Context): KNDatabase {
            synchronized(KNDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        KNDatabase::class.java,
                        "kn_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}