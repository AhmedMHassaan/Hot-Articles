package com.ahmedmhassaan.orangetask.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ahmedmhassaan.domain.models.DomainArticle

open class ArticlesDiffUtil(
    private val oldList: List<DomainArticle>,
    private val newList: List<DomainArticle>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url == newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].url == newList[newItemPosition].url -> true
            oldList[oldItemPosition].title == newList[newItemPosition].title -> true
            oldList[oldItemPosition].publishedAt == newList[newItemPosition].publishedAt -> true
            else -> false

        }

    }
}