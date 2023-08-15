package com.ahmedmhassaan.data.local.database

import androidx.test.filters.SmallTest
import com.ahmedmhassaan.data.local.model.ArticleEntity
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
//@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@SmallTest
class ArticlesDaoTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

//    @get:Rule
//    val instantTaskExecutor = InstantTaskExecutorRule()

    @Inject
    @Named("test_database")
    lateinit var database: NewsDatabase

    @Inject
    lateinit var articlesDao: ArticlesDao


    @Before
    fun setUp() {
        hiltRule.inject()
    }


    @Test
    fun test_cacheArticles() {
        runTest {
            val articles = mutableListOf<ArticleEntity>(
                ArticleEntity(
                    "t",
                    "desc",
                    "author",
                    "url",
                    "source",
                    "content",
                    "2010-11-10",
                    "image",
                    false,
                    0
                ),
                ArticleEntity(
                    "title",
                    "desc",
                    "author",
                    "url1",
                    "source",
                    "content",
                    "2010-11-10",
                    "image",
                    false,
                    0
                ),
                ArticleEntity(
                    "title",
                    "desc",
                    "author",
                    "url2",
                    "source",
                    "content",
                    "2010-11-10",
                    "image",
                    false,
                    0
                ),
                ArticleEntity(
                    "title",
                    "desc",
                    "author",
                    "url3",
                    "source",
                    "content",
                    "2010-11-10",
                    "image",
                    false,
                    0
                )
            )

            articlesDao.cacheArticlesInLocalDb(articles)

            val result = articlesDao.loadCachedArticles()

            Truth.assertThat(result).isEqualTo(articles)
            Truth.assertThat(result).contains(articles[0])

        }
    }

    @After
    fun tearDown() {
        database.close()
    }
}