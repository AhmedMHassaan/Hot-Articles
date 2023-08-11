package com.ahmedmhassaan.orangetask.presentation.viewholders

import android.view.View
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.databinding.ItemNewsBinding
import com.ahmedmhassaan.orangetask.presentation.base.viewholder.BaseViewHolder

class ArticlesViewHolder(itemView: View) :
    BaseViewHolder<DomainArticle, ItemNewsBinding>(itemView) {
    override fun onBind(model: DomainArticle?) {
        binding.article = model
    }
}