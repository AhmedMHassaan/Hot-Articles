package com.ahmedmhassaan.orangetask.presentation.ui.news.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmedmhassaan.domain.usecase.GetSavedLanguageUseCase
import com.ahmedmhassaan.domain.usecase.SaveAppLanguageUseCase
import com.ahmedmhassaan.orangetask.presentation.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveLanguageUseCase: SaveAppLanguageUseCase,
) : BaseViewModel() {

    private val _languageSaved = MutableLiveData<Boolean>()
    val languageSaved: LiveData<Boolean> = _languageSaved


    fun saveLanguage(lang: String) {
        saveLanguageUseCase.invoke(lang).dataHandling(
            success = {
                _languageSaved.postValue(it)
            },
            showLoading = {},
            showError = {}
        )
    }
}