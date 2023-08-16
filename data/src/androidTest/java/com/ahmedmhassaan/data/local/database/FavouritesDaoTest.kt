@file:OptIn(ExperimentalCoroutinesApi::class)

package com.ahmedmhassaan.data.local.database

import androidx.test.filters.SmallTest
import com.ahmedmhassaan.data.ext.toDomainArticle
import com.ahmedmhassaan.data.local.model.ArticleEntity
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.DomainArticleSource
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
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
@SmallTest
@HiltAndroidTest
class FavouritesDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_database")
    lateinit var database: NewsDatabase

    lateinit var favouritesDao: FavouritesDao
    lateinit var cacheDao: ArticlesDao
    lateinit var domainArticle: DomainArticle
    lateinit var entityArticle: ArticleEntity

    @Before
    fun setUp() {
        hiltRule.inject()
        favouritesDao = database.favouritesDao()
        cacheDao = database.articlesDao()

        domainArticle = DomainArticle(
            DomainArticleSource(null, "source"),
            "author",
            "title",
            "desc",
            "url_",
            "image",
            "date",
            "content"
        )

        entityArticle = ArticleEntity(
            "title", "desc", "author", "url_", "source", "content", "date", "image", false, 1
        )
    }


    @Test
    fun test_insert_into_favourites() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(entityArticle))
            val insertedId = favouritesDao.addArticleToFav(entityArticle.url)
            assertWithMessage("InsertedId = $insertedId").that(insertedId).isGreaterThan(0)
        }
    }

    @Test
    fun test_load_favourites() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(entityArticle))
            favouritesDao.addArticleToFav(entityArticle.url)
            val favList = favouritesDao.loadAllFavourites()

            assertWithMessage("Fav List is $favList").that(favList).hasSize(1)

        }
    }

    @Test
    fun test_that_propertyIsInFav_is_true() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(entityArticle))
            favouritesDao.addArticleToFav(entityArticle.url)
            val favList = favouritesDao.loadAllFavourites()
            val favouritedArticle = favList[0]
            assertThat(favouritedArticle.isInFav).isTrue()
        }
    }

    @Test
    fun test_that_propertyPage_is_1() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(entityArticle))
            favouritesDao.addArticleToFav(entityArticle.url)
            val favList = favouritesDao.loadAllFavourites()
            val favouritedArticle = favList[0]
            assertThat(favouritedArticle.page).isEqualTo(1)
        }
    }

    @Test
    fun test_convert_from_favouritesItem_to_domainArticle() {
        runTest {
            cacheDao.cacheArticlesInLocalDb(mutableListOf(entityArticle))
            favouritesDao.addArticleToFav(entityArticle.url)
            val favList = favouritesDao.loadAllFavourites()
            val favouritedArticle = favList[0]
            assertThat(favouritedArticle.toDomainArticle()).isEqualTo(domainArticle)
        }
    }

    @Test
    fun test_remove_item_from_favourite() {
        runTest {

            cacheDao.cacheArticlesInLocalDb(mutableListOf(entityArticle))
            favouritesDao.addArticleToFav("url")
            val response = favouritesDao.removeArticleFromFav(entityArticle)
            assertWithMessage("Response is $response").that(response).isGreaterThan(0)
        }
    }
}