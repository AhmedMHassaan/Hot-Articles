package com.ahmedmhassaan.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedmhassaan.data.local.model.ArticleEntity

@Dao
interface ArticlesDao {

    @Query("select * from articles")
    suspend fun loadCachedArticles(): List<ArticleEntity>

    @Insert(ArticleEntity::class, OnConflictStrategy.REPLACE)
    suspend fun cacheArticlesInLocalDb(articles: List<ArticleEntity>)

}