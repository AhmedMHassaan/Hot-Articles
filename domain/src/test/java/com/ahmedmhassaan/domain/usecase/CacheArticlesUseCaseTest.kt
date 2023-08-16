package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.DomainArticleSource
import com.ahmedmhassaan.domain.repos.FakeLocalRepository
import com.ahmedmhassaan.domain.repos.LocalCacheArticlesRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestScope
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CacheArticlesUseCaseTest {

    lateinit var repository: LocalCacheArticlesRepository
    lateinit var cacheArticleUseCase: CacheArticlesUseCase
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
        repository = FakeLocalRepository()
        cacheArticleUseCase = CacheArticlesUseCase(repository)

    }


    @Test
    fun `test that use case is return Resurce_Sucess`() {
        val list = mutableListOf<DomainArticle>(
            domainArticle,
            domainArticle,
            domainArticle,
            domainArticle
        )

        cacheArticleUseCase.execute(list).onEach {
            val cachedList = repository.loadCashedArticles()
            assertThat(cachedList).isEqualTo(list)
        }.launchIn(TestScope())
    }


}