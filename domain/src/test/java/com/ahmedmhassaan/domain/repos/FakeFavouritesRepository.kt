package com.ahmedmhassaan.domain.repos

import com.ahmedmhassaan.domain.models.DomainArticle

class FakeFavouritesRepository : FavouritesRepository {
    private val favList = mutableListOf<DomainArticle>()

    override suspend fun addArticleToFav(article: DomainArticle): Boolean {
        return favList.add(article)
    }

    override suspend fun loadFavourites(): List<DomainArticle> {
        return favList
    }

    override suspend fun removeArticleFromFav(article: DomainArticle): Boolean {
        return favList.remove(article)
    }
}