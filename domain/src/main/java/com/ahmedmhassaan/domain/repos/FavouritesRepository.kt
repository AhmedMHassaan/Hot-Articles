package com.ahmedmhassaan.domain.repos

import com.ahmedmhassaan.domain.models.DomainArticle

interface FavouritesRepository {

    suspend fun addArticleToFav(article: DomainArticle): Boolean

    suspend fun loadFavourites(): List<DomainArticle>

    suspend fun removeArticleFromFav(article: DomainArticle): Boolean

}