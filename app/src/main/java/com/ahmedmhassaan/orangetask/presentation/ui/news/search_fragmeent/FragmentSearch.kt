package com.ahmedmhassaan.orangetask.presentation.ui.news.search_fragmeent

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.databinding.FragmentSearchBinding
import com.ahmedmhassaan.orangetask.presentation.adapters.ArticlesAdapter
import com.ahmedmhassaan.orangetask.presentation.base.fragment.BaseBindFragment
import com.ahmedmhassaan.orangetask.utils.setupWithAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSearch : BaseBindFragment<FragmentSearchBinding>() {

    private val articlesAdapter = ArticlesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recViewArticles.setupWithAdapter(
            articlesAdapter,
            StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
        )
    }

    override fun getLayoutId() = R.layout.fragment_search
}