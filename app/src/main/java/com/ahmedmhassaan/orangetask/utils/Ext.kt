package com.ahmedmhassaan.orangetask.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.IntegerRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.presentation.base.adapters.BaseRecyclerAdapter
import com.cooltechworks.views.shimmer.ShimmerRecyclerView

fun ViewGroup.inflate(@LayoutRes layoutIntRes: Int): View {
    return LayoutInflater.from(this.context).inflate(
        layoutIntRes,
        this,
        false
    )
}


fun ShimmerRecyclerView.setupWithAdapter(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>) {

    setupWithAdapter(
        adapter,
        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    )
}

fun ShimmerRecyclerView.setupWithAdapter(
    adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
    manager: RecyclerView.LayoutManager
) {
    this.adapter = adapter
    this.layoutManager = manager
}

fun ShimmerRecyclerView.setLoading(
    isLoading: Boolean = true
) {
    when (isLoading) {
        true -> showShimmerAdapter()
        false -> hideShimmerAdapter()
    }
}



