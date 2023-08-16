package com.ahmedmhassaan.orangetask.presentation.ui.news.favourites

import android.view.View
import android.view.View.OnClickListener
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.databinding.ItemFavouritedArticleBinding
import com.ahmedmhassaan.orangetask.presentation.base.viewholder.BaseViewHolder
import dagger.hilt.android.lifecycle.HiltViewModel


class FavouritesViewHolder(itemView: View, var listener: OnFavItemClickedListener?) :
    BaseViewHolder<DomainArticle, ItemFavouritedArticleBinding>(itemView) {
    override fun onBind(model: DomainArticle?) {
        binding.article = model
        binding.listener = listener
    }
}