package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.BaseFlowUseCase
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.LocalCacheArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddArticleToFavouriteUseCase @Inject constructor(
    private val localArticlesRepository: LocalCacheArticlesRepository
) : BaseFlowUseCase<DomainArticle, Boolean>() {
    override fun execute(request: DomainArticle): Flow<Resource<Boolean>> = flow {
        val response = localArticlesRepository.addArticleToFav(
            request
        )
        emit(Resource.Success(response))
    }
}