package com.ahmedmhassaan.data.repository

import androidx.test.filters.SmallTest
import com.ahmedmhassaan.data.ext.toEntityArticle
import com.ahmedmhassaan.data.local.database.ArticlesDao
import com.ahmedmhassaan.data.local.database.FavouritesDao
import com.ahmedmhassaan.data.local.database.NewsDatabase
import com.ahmedmhassaan.data.local.datasources.FavouritesDataSource
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.DomainArticleSource
import com.ahmedmhassaan.domain.repos.FavouritesRepository
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@SmallTest
class FavouritesRepositoryImplTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var favouritesRepository: FavouritesRepository
    private lateinit var favouritesDataSource: FavouritesDataSource

    @Inject
    @Named("test_database")
    lateinit var database: NewsDatabase

    private lateinit var favouritesDao: FavouritesDao
    private lateinit var cacheDao: ArticlesDao
    private val domainEntity = DomainArticle(
        DomainArticleSource(null, "source"),
        "authour",
        "title",
        "desc",
        "url",
        "urlImg",
        "date",
        null
    )


    @Before
    fun setUp() {
        hiltRule.inject()

        favouritesDao = database.favouritesDao()
        cacheDao = database.articlesDao()

        favouritesDataSource = FavouritesDataSource(favouritesDao)
        favouritesRepository = FavouritesRepositoryImpl(favouritesDataSource)

    }


    @Test
    fun checkCachingArticles() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(domainEntity.toEntityArticle()))
            val list = cacheDao.loadCachedArticles()
            assertThat(list).hasSize(1)

        }
    }

    @Test
    fun checkInsertingToFavourites_retuenTrueIfInserted() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(domainEntity.toEntityArticle()))
            val response = favouritesRepository.addArticleToFav(article = domainEntity)
            assertThat(response).isTrue()
        }
    }

    @Test
    fun checkFavouritedItemIsEqualToInsertedItem() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(domainEntity.toEntityArticle()))
            favouritesRepository.addArticleToFav(article = domainEntity)
            val favList = favouritesRepository.loadFavourites()
            val item = favList[0]
            assertThat(item).isEqualTo(domainEntity)
        }
    }


    @Test
    fun test_removingItemFromFav_returnTrue() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(domainEntity.toEntityArticle()))
            favouritesRepository.addArticleToFav(article = domainEntity)
            val response = favouritesRepository.removeArticleFromFav(domainEntity)
            assertThat(response).isTrue()
        }
    }

    @Test
    fun test_removingItemFav_favListWillBeEmpty() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(domainEntity.toEntityArticle()))
            favouritesRepository.addArticleToFav(article = domainEntity)
            favouritesRepository.removeArticleFromFav(domainEntity)
            val favList = favouritesRepository.loadFavourites()
            assertThat(favList).isEmpty()
        }
    }
}