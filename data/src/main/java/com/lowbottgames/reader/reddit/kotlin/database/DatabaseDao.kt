package com.lowbottgames.reader.reddit.kotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: FeedEntity)

    @Query("SELECT * FROM feed_table")
    fun getAll() : List<FeedEntity>

}