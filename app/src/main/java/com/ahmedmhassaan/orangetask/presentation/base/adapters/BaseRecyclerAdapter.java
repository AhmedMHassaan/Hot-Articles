package com.ahmedmhassaan.orangetask.presentation.base.adapters;


import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedmhassaan.orangetask.R;
import com.ahmedmhassaan.orangetask.presentation.base.viewholder.BaseViewHolder;
import com.ahmedmhassaan.orangetask.utils.ToastMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<MODEL, BINDING extends ViewDataBinding, VIEW_HOLDER extends BaseViewHolder<MODEL, BINDING>> extends RecyclerView.Adapter<VIEW_HOLDER> {

    final ArrayList<MODEL> arrayList = new ArrayList<>();
//    VIEW_HOLDER viewHolder;



    @NonNull
    @Override
    public abstract VIEW_HOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType);


    @Override
    public void onBindViewHolder(@NonNull VIEW_HOLDER holder, int position) {
        holder.onBind(getItem(position));
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public MODEL getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(MODEL model) {
        try {

            arrayList.add(model);

//            notifyItemInserted(getItemCount() - 2);
            notifyItemInserted(getItemCount() - 1);
/*
            notifyItemInserted(getItemCount());
            notifyItemInserted(getItemCount() + 1);
            notifyItemInserted(getItemCount() + 2);
*/

        } catch (Exception e) {
            ToastMessage.INSTANCE.error("غير قادر علي الإضافة");
        }
//        notifyDataSetChanged();
    }


    public void deleteItem(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (Exception e) {
            ToastMessage.INSTANCE.error("غير قادر علي حذف العنصر");
        }
    }

    public void updateItem(int position, MODEL model) {
        try {
            arrayList.set(position, model);
            notifyItemChanged(position, model);

        } catch (Exception e) {

            ToastMessage.INSTANCE.error("غير قادر علي تعديل العنصر");
        }
    }

    public List<MODEL> getList() {
        return arrayList;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VIEW_HOLDER holder) {
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.item_animation_transaction));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VIEW_HOLDER holder) {
        holder.itemView.clearAnimation();
//        Glide.get(holder.itemView.getContext()).clearDiskCache();
//        Glide.get(holder.itemView.getContext()).clearMemory();

    }


    public boolean isEmpty() {
        return arrayList.size() == 0;
    }


    public abstract void updateList(List<MODEL> list) ;


    public void updateListWithNoNullItems(ArrayList<MODEL> list) {
        this.arrayList.clear();
        for (MODEL model : list) {
            if (model != null)
                this.arrayList.add(model);
        }
        notifyDataSetChanged();
    }

    public void updateListWithPaginationNoNullItems(ArrayList<MODEL> paginationList) {
        for (MODEL model : paginationList) {
            if (model != null)
                addItem(model);
        }
    }

    public void updateListWithPagination(ArrayList<MODEL> paginationList) {
        for (MODEL model : paginationList) {
            addItem(model);
        }
    }


    public void clear() {
        this.arrayList.clear();
        notifyDataSetChanged();
    }
}
