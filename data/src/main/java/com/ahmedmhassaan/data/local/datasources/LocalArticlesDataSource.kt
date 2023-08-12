package com.ahmedmhassaan.data.local.datasources

import com.ahmedmhassaan.data.local.model.ArticleEntity
import com.ahmedmhassaan.data.local.database.ArticlesDao
import javax.inject.Inject

class LocalArticlesDataSource @Inject constructor(
    private val articlesDao: ArticlesDao
) {

    suspend fun cacheArticles(articles: List<ArticleEntity>) {
        articlesDao.cacheArticlesInLocalDb(articles)
    }

    suspend fun loadCachedArticles(page: Int): List<ArticleEntity> {
        return articlesDao.loadCachedArticles()
    }
}