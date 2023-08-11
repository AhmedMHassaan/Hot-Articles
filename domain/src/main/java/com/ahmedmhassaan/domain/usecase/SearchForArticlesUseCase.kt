package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.BaseFlowUseCase
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchForArticlesUseCase @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : BaseFlowUseCase<SearchForArticlesUseCase.Request, List<DomainArticle>>() {
    override fun execute(request: Request): Flow<Resource<List<DomainArticle>>> = flow {
        val response = articlesRepository.searchForArticles(
            request.query, request.pageNumber, request.from, request.language
        )
    }

    data class Request(
        val query: String, val pageNumber: Int, val from: String, val language: String

    )
}