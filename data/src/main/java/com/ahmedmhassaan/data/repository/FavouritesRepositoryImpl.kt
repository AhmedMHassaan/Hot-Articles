package com.ahmedmhassaan.data.repository

import com.ahmedmhassaan.data.ext.toDomainArticle
import com.ahmedmhassaan.data.ext.toEntityArticle
import com.ahmedmhassaan.data.local.datasources.FavouritesDataSource
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.repos.FavouritesRepository
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val favDataSource: FavouritesDataSource,

    ) : FavouritesRepository {

    override suspend fun addArticleToFav(article: DomainArticle): Boolean {
        return favDataSource.addArticleToFavourite(article.toEntityArticle()) > 0
    }

    override suspend fun loadFavourites(): List<DomainArticle> {
        return favDataSource.loadFavourites().map {
            it.toDomainArticle()
        }
    }

    override suspend fun removeArticleFromFav(article: DomainArticle): Boolean {
        return favDataSource.removeArticleFromFav(article.toEntityArticle()) > 0
    }
}