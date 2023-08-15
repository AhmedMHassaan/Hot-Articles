package com.ahmedmhassaan.data.local.datasources

import com.ahmedmhassaan.data.local.model.ArticleEntity
import com.ahmedmhassaan.data.local.database.FavouritesDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouritesDataSource @Inject constructor(
    private val favouritesDao: FavouritesDao
) {

    suspend fun addArticleToFavourite(articleEntity: ArticleEntity): Int {
        return favouritesDao.addArticleToFav(articleEntity.url)
    }

    suspend fun loadFavourites(): List<ArticleEntity> {
        return favouritesDao.loadAllFavourites()
    }

    suspend fun removeArticleFromFav(articleEntity: ArticleEntity): Int {
        return favouritesDao.removeArticleFromFav(articleEntity)
    }

}