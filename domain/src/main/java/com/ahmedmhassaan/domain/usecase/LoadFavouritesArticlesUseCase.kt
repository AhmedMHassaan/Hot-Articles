package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.BaseFlowUseCase
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LoadFavouritesArticlesUseCase @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) : BaseFlowUseCase<Any?, List<DomainArticle>>() {

    override fun execute(request: Any?): Flow<Resource<List<DomainArticle>>> = flow {
        val favList = favouritesRepository.loadFavourites()
        emit(Resource.Success(favList))
    }
}