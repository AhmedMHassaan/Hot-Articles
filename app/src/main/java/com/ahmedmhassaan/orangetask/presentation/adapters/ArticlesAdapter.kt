package com.ahmedmhassaan.orangetask.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.databinding.ItemNewsBinding
import com.ahmedmhassaan.orangetask.presentation.base.adapters.BasePaginRecyclerAdapter
import com.ahmedmhassaan.orangetask.presentation.base.adapters.BaseRecyclerAdapter
import com.ahmedmhassaan.orangetask.presentation.viewholders.ArticlesViewHolder
import com.ahmedmhassaan.orangetask.utils.inflate
import java.util.ArrayList

class ArticlesAdapter() :
    BasePaginRecyclerAdapter<DomainArticle, ItemNewsBinding, ArticlesViewHolder>(diffCallback = object :
        DiffUtil.ItemCallback<DomainArticle>() {
        override fun areItemsTheSame(oldItem: DomainArticle, newItem: DomainArticle): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: DomainArticle, newItem: DomainArticle): Boolean {
            return when {
                oldItem.url == newItem.url -> true
                oldItem.title == newItem.title -> true
                oldItem.publishedAt == newItem.publishedAt -> true
                else -> false
            }
        }
    }) {

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(
            parent.inflate(R.layout.item_news)
        )
    }


}