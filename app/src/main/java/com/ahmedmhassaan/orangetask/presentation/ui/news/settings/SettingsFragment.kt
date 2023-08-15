package com.ahmedmhassaan.orangetask.presentation.ui.news.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ahmedmhassaan.orangetask.presentation.ui.news.MainActivity
import com.ahmedmhassaan.orangetask.databinding.FragmentSettingsBinding
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.presentation.base.fragment.BaseBindFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseBindFragment<FragmentSettingsBinding>(), View.OnClickListener {

    private val settingsViewModel by viewModels<SettingsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.listener = this
        observeViewModel()
    }

    private fun observeViewModel() {
        settingsViewModel.languageSaved.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(
                    Intent(
                        context,
                        MainActivity::class.java
                    )
                )
                activity?.finish()
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_settings

    override fun onClick(p0: View?) {
        when (p0) {
            binding.cardArabic -> {
                changeLanguage("ar")
            }

            binding.cardEnglish -> {
                changeLanguage("en")
            }


            binding.cardDevice -> {
                changeLanguage("")
            }
        }
    }

    private fun changeLanguage(lang: String) {
        settingsViewModel.saveLanguage(lang)
    }
}