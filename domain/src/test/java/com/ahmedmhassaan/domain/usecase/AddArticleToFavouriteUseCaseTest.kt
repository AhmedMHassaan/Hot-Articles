package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.DomainArticleSource
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.FavouritesRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestScope
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
//@SmallTest
//@RunWith(AndroidJUnit4::class)
class AddArticleToFavouriteUseCaseTest {

    lateinit var repository: FavouritesRepository
    lateinit var addArticleToFavouriteUseCase: AddArticleToFavouriteUseCase
    val domainArticle = DomainArticle(
        DomainArticleSource(null, "source"),
        "author",
        "title",
        "desc",
        "url",
        "url",
        "date",
        "content"
    )

    @Before
    fun setUp() {
        repository = com.ahmedmhassaan.domain.repos.FakeFavouritesRepository()
        addArticleToFavouriteUseCase = AddArticleToFavouriteUseCase(
            repository
        )
    }


    @Test
    fun test_insertToFavUseCase() {
        addArticleToFavouriteUseCase.execute(domainArticle).onEach {
            Truth.assertThat(it).isEqualTo(Resource.Success(true))
        }.launchIn(TestScope())
    }

    @After
    fun tearDown() {
    }
}