package com.ahmedmhassaan.domain.usecase

import androidx.paging.PagingData
import com.ahmedmhassaan.domain.BaseFlowUseCase
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchForArticlesUseCase @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : BaseFlowUseCase<Unit?, Flow<PagingData<DomainArticle>>>() {
    override fun execute(request: Unit?): Flow<Resource<Flow<PagingData<DomainArticle>>>> = flow {
        val response = articlesRepository.searchForArticles()
        emit(Resource.Success(response))
    }

}