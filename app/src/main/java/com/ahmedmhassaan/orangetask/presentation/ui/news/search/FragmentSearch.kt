package com.ahmedmhassaan.orangetask.presentation.ui.news.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.databinding.FragmentSearchBinding
import com.ahmedmhassaan.orangetask.presentation.adapters.ArticlesAdapter
import com.ahmedmhassaan.orangetask.presentation.base.fragment.BaseBindFragment
import com.ahmedmhassaan.orangetask.utils.setLoading
import com.ahmedmhassaan.orangetask.utils.setupWithAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSearch : BaseBindFragment<FragmentSearchBinding>(), View.OnClickListener {

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this)[SearchViewModel::class.java]
    }

    private val articlesAdapter = ArticlesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        observeViewModel()
        setupRecycler()
        events()

    }

    private fun observeViewModel() {

        searchViewModel.searchResultLiveData.observe(viewLifecycleOwner) {
            articlesAdapter.submitData(lifecycle, it)
            Log.d(
                "APP_TAG",
                "FragmentSearch - observeViewModel:  data submited and new size is ${articlesAdapter.itemCount}"
            )
        }

        searchViewModel.error.observe(viewLifecycleOwner) {
            showAlertDialog(
                title = getString(R.string.error),
                msg = it,
            )
        }

        searchViewModel.progress.observe(viewLifecycleOwner) {
            binding.recViewArticles.setLoading(isLoading = it)
        }

    }

    private fun events() {
        binding.listener = this
        binding.txtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.search(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupRecycler() {
        binding.recViewArticles.setupWithAdapter(
            articlesAdapter,
//            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        )
        binding.recViewArticles.showShimmerAdapter()
        Log.d("APP_TAG", "FragmentSearch - setupRecycler:  shimmer is started")
    }

    override fun getLayoutId() = R.layout.fragment_search

    override fun onClick(p0: View?) {
        when (p0) {
            binding.settingsLayout -> {
                findNavController()
                    .navigate(
                        FragmentSearchDirections.actionFragmentSearchToFragmentSettings()
                    )
            }
        }
    }
}