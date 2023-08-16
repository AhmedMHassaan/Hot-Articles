package com.ahmedmhassaan.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedmhassaan.data.local.model.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 2
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    abstract fun favouritesDao(): FavouritesDao

}