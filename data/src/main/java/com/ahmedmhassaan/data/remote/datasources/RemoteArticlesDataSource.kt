package com.ahmedmhassaan.data.remote.datasources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ahmedmhassaan.data.local.database.ArticlesDao
import com.ahmedmhassaan.data.remote.api.ArticleService
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


    suspend fun topHeadLines() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            TopHeadLinesArticlesPagingDataSource(
                service = articleService,
                articlesDao = articlesDao

            )
        }
    ).flow
}