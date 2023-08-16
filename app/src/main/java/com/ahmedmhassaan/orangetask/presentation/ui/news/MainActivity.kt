package com.ahmedmhassaan.orangetask.presentation.ui.news

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.databinding.ActivityMainBinding
import com.ahmedmhassaan.orangetask.presentation.base.LocatableActivity
import com.ahmedmhassaan.orangetask.presentation.base.activity.BaseActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : BaseActivityBinding<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragmentActivityHome.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

    }

    override fun getLayoutId() = R.layout.activity_main

}