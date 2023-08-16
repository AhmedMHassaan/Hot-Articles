package com.ahmedmhassaan.orangetask.presentation.ui.news.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.databinding.FragmentSearchBinding
import com.ahmedmhassaan.orangetask.presentation.adapters.ArticlesAdapter
import com.ahmedmhassaan.orangetask.presentation.base.fragment.BaseBindFragment
import com.ahmedmhassaan.orangetask.utils.ToastMessage
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

        searchViewModel.addedToFav.observe(viewLifecycleOwner) {
            if (it) {
                ToastMessage.success(context, getString(R.string.added_to_fav_successfully))
            } else {
                Toast.makeText(context, "Not Added", Toast.LENGTH_SHORT).show()
            }
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

        articlesAdapter.listener = object : AdapterOnClickEvents {
            override fun showArticleDetails(article: DomainArticle) {
                try {
                    findNavController()
                        .navigate(
                            FragmentSearchDirections.actionFragmentSearchToFragmentDetails(
                                article
                            )
                        )
                } catch (e: Exception) {
                    ToastMessage.error(context!!, e.message.toString())
                }
            }

            override fun addToFavourite(article: DomainArticle) {
                searchViewModel.addArticleToFavourites(article)
            }

            override fun shareArticle(article: DomainArticle) {
                shareArticleToOuterApp(article)
            }
        }
    }

    private fun shareArticleToOuterApp(article: DomainArticle) {
        val textShare = """
            ${getString(R.string.title)} : ${article.title},
        ${getString(R.string.you_can_see_more_on)} : 
            ${article.url}
        """.trimIndent()

        val intent = Intent()
            .setAction(Intent.ACTION_SEND)
            .putExtra(Intent.EXTRA_TEXT, textShare)
            .setType("text/plain")
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)

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