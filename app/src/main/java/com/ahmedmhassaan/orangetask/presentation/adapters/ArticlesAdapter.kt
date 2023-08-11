package com.ahmedmhassaan.orangetask.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.databinding.ItemNewsBinding
import com.ahmedmhassaan.orangetask.presentation.base.adapters.BaseRecyclerAdapter
import com.ahmedmhassaan.orangetask.presentation.viewholders.ArticlesViewHolder
import com.ahmedmhassaan.orangetask.utils.inflate
import java.util.ArrayList

class ArticlesAdapter() :
    BaseRecyclerAdapter<DomainArticle, ItemNewsBinding, ArticlesViewHolder>() {

    override fun updateList(updatedList: List<DomainArticle>) {
        val diffResult = DiffUtil.calculateDiff(
            ArticlesDiffUtil(
                list, updatedList
            )
        )

        this.list.clear();
        this.list.addAll(updatedList);

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(parent.inflate(R.layout.item_news))
    }


}