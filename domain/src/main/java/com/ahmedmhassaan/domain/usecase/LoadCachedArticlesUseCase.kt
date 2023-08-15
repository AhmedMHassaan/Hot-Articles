package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.BaseFlowUseCase
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.LocalCacheArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadCachedArticlesUseCase @Inject constructor(
    private val localArticlesRepository: LocalCacheArticlesRepository
) : BaseFlowUseCase<Any?, List<DomainArticle>>() {
    override fun execute(request: Any?): Flow<Resource<List<DomainArticle>>> = flow {
        val response = localArticlesRepository.loadCashedArticles(
//            request.currentPage,
//            request.language
        )
        emit(Resource.Success(response))
    }

//    data class Request(
////        val currentPage: Int,
////        val language: String
//    )
}