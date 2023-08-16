package com.ahmedmhassaan.orangetask.presentation.ui.news.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.databinding.FragmentFavouritesBinding
import com.ahmedmhassaan.orangetask.presentation.base.fragment.BaseBindFragment
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.utils.ToastMessage
import com.ahmedmhassaan.orangetask.utils.setLoading
import com.ahmedmhassaan.orangetask.utils.setupWithAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseBindFragment<FragmentFavouritesBinding>() {

    private val favouritesAdapter = FavouritesArticlesAdapter()
    private val favouritesViewModel by viewModels<FavouritesViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recViewArticles.setupWithAdapter(favouritesAdapter)

        observeViewModel()
        events()

    }

    private fun events() {
        favouritesAdapter.listener = object : OnFavItemClickedListener {
            override fun showArticleDetails(article: DomainArticle) {
                try {
                    findNavController().navigate(
                            FavouritesFragmentDirections.actionFragmentFavToFragmentDetails(article)
                        )
                } catch (e: Exception) {
                    ToastMessage.error(context!!, e.message.toString())
                }
            }

            override fun removeFromFav(article: DomainArticle) {
                favouritesViewModel.removeArticleFromFav(article)
            }
        }

    }

    private fun observeViewModel() {
        favouritesViewModel.progress.observe(viewLifecycleOwner) {
            binding.recViewArticles.setLoading(it)
        }

        favouritesViewModel.error.observe(viewLifecycleOwner) {
            showAlertDialog(title = getString(R.string.error), msg = it)
        }

        favouritesViewModel.favList.observe(viewLifecycleOwner) {
            favouritesAdapter.updateList(it)
        }

        favouritesViewModel.removedFromFav.observe(viewLifecycleOwner) {
            if (it) {
                ToastMessage.success(context, getString(R.string.removed_from_fav))
            }
        }
    }


    override fun getLayoutId() = R.layout.fragment_favourites
}