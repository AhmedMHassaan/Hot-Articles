package com.ahmedmhassaan.orangetask.presentation.base.adapters

import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ahmedmhassaan.orangetask.presentation.base.viewholder.BaseViewHolder

abstract class BasePaginRecyclerAdapter<MODEL : Any, BINDING : ViewDataBinding, VIEW_HOLDER : BaseViewHolder<MODEL, BINDING>>(
    diffCallback: DiffUtil.ItemCallback<MODEL>
) :
    PagingDataAdapter<MODEL, VIEW_HOLDER>(
        diffCallback

    ) {


}