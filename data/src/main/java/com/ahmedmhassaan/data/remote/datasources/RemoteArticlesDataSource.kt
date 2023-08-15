package com.ahmedmhassaan.data.remote.datasources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.util.query
import com.ahmedmhassaan.data.local.database.ArticlesDao
import com.ahmedmhassaan.data.local.database.LanguageSharedPreferences
import com.ahmedmhassaan.data.model.Article
import com.ahmedmhassaan.data.remote.api.ArticleService
import com.ahmedmhassaan.domain.repos.LanguageRepository
import javax.inject.Inject

class RemoteArticlesDataSource @Inject constructor(
    private val articleService: ArticleService,
    private val articlesDao: ArticlesDao
//    private val languageSharedPreferences: LanguageSharedPreferences
) {

    suspend fun search(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                ArticlesPagingDataSource(
                    service = articleService,
                    query = query,
                    articlesDao = articlesDao

//                    languageSharedPreferences.getLanguage()
                )
            },
        ).flow
}