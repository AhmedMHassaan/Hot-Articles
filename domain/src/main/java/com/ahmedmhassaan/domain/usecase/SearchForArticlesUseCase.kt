package com.ahmedmhassaan.domain.usecase

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahmedmhassaan.domain.BaseFlowUseCase
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.ArticlesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchForArticlesUseCase @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : BaseFlowUseCase<String, Flow<PagingData<DomainArticle>>>() {
    override fun execute(request: String): Flow<Resource<Flow<PagingData<DomainArticle>>>> = flow {
        val response =
            articlesRepository.searchForArticles(request).cachedIn(CoroutineScope(Dispatchers.IO))
        emit(Resource.Success(response))
    }

}