package com.ahmedmhassaan.orangetask.presentation.ui.news

import android.os.Bundle
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.presentation.base.LocatableActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
//    private val getSavedLanguageUseCase: GetSavedLanguageUseCase
) : LocatableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}