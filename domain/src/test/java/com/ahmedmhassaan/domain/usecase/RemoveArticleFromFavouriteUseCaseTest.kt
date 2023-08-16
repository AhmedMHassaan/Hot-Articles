package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.DomainArticleSource
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.FakeFavouritesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class RemoveArticleFromFavouriteUseCaseTest {

    private lateinit var useCase: RemoveArticleFromFavouriteUseCase
    private lateinit var repository: FakeFavouritesRepository
    private lateinit var article: DomainArticle

    @Before
    fun setUp() {
        article = DomainArticle(
            DomainArticleSource(null, ""),
            "auth",
            "title",
            "desc",
            "url",
            "urlImage",
            "date",
            "content"
        )

        repository = FakeFavouritesRepository()
        useCase = RemoveArticleFromFavouriteUseCase(repository)
    }

    @Test
    fun test_that_articles_removed() {
        runTest {
            repository.addArticleToFav(article)
            useCase.execute(article).onEach {
                assertEquals("result is $it", it, Resource.Success(true))
            }.launchIn(this)
        }

    }
}