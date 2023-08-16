package com.ahmedmhassaan.orangetask.presentation.ui.news.favourites

import android.view.View.OnClickListener
import android.view.ViewGroup

import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.databinding.ItemFavouritedArticleBinding
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.presentation.adapters.BaseRecyclerAdapter
import com.ahmedmhassaan.orangetask.utils.inflate

class FavouritesArticlesAdapter :
    BaseRecyclerAdapter<DomainArticle, ItemFavouritedArticleBinding, FavouritesViewHolder>() {
    var listener:OnFavItemClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(
            parent.inflate(R.layout.item_favourited_article),
            listener
        )
    }
}