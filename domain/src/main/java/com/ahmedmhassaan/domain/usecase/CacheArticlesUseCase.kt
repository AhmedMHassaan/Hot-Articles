package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.BaseFlowUseCase
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.LocalCacheArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CacheArticlesUseCase @Inject constructor(
    private val localArticlesRepository: LocalCacheArticlesRepository
) : BaseFlowUseCase<List<DomainArticle>, Unit?>() {
    override fun execute(request: List<DomainArticle>): Flow<Resource<Unit?>> = flow {
        val response = localArticlesRepository.cacheArticles(request)
        emit(Resource.Success(response))
    }
}