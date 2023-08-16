package com.ahmedmhassaan.data.remote.datasources

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmedmhassaan.data.ext.toEntityArticle
import com.ahmedmhassaan.data.local.database.ArticlesDao
import com.ahmedmhassaan.data.model.Article
import com.ahmedmhassaan.data.model.ArticlesResponse
import com.ahmedmhassaan.data.remote.api.ArticleService
import com.ahmedmhassaan.data.throwables.HostApiError
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class TopHeadLinesArticlesPagingDataSource @Inject constructor(
    private val service: ArticleService,
    private val articlesDao: ArticlesDao,
//    private val query: String,
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {

            val position = params.key ?: 1
            val country = Locale.getDefault().country


            Log.d(
                "APP_TAG",
                "TopHeadLinesArticlesPagingDataSource - load:    country is $country and size is ${params.loadSize} and position is $position"
            )
            val response: ArticlesResponse = service.topHeadLines(
                country = country, pageSize = params.loadSize, currentPage = position
            ).getOrThrow()

            val articles = response.articles

            val entities = articles.map {
                it.toEntityArticle(position)
            }

            articlesDao.cacheArticlesInLocalDb(entities) // to cache articles


            LoadResult.Page(
                data = articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (articles.isEmpty()) null else position + 1
            )


        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: HostApiError) {
            LoadResult.Error(e)
        }
    }
}