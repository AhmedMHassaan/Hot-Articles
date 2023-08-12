package com.ahmedmhassaan.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ahmedmhassaan.data.local.model.ArticleEntity

@Dao
interface FavouritesDao {

    @Insert
    suspend fun addArticleToFav(article: ArticleEntity): Long

    @Query("select * from articles where isInFav = 1 ")
    suspend fun loadAllFavourites(): List<ArticleEntity>

    @Delete
    suspend fun removeArticleFromFav(article: ArticleEntity): Int
}