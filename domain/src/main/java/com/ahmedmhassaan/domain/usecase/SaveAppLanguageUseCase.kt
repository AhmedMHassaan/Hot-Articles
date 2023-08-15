package com.ahmedmhassaan.domain.usecase

import com.ahmedmhassaan.domain.BaseFlowUseCase
import com.ahmedmhassaan.domain.models.Resource
import com.ahmedmhassaan.domain.repos.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveAppLanguageUseCase @Inject constructor(
    private val languageRepository: LanguageRepository
) : BaseFlowUseCase<String, Boolean>() {
    override fun execute(request: String): Flow<Resource<Boolean>> = flow {
        val res = languageRepository.saveLanguage(request)
        emit(Resource.Success(res))
    }
}