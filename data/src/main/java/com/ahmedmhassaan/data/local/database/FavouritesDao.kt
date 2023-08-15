package com.ahmedmhassaan.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ahmedmhassaan.data.local.model.ArticleEntity

@Dao
interface FavouritesDao {

    @Query("update articles set isInFav = 1 where url = :url")
    suspend fun addArticleToFav(url: String): Int

    @Query("select * from articles where isInFav = 1 ")
    suspend fun loadAllFavourites(): List<ArticleEntity>

    @Delete
    suspend fun removeArticleFromFav(article: ArticleEntity): Int
}