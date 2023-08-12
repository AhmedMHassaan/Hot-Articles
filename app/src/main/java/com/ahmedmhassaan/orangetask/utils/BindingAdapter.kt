package com.ahmedmhassaan.orangetask.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ahmedmhassaan.orangetask.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority

class BindingAdapter {


    companion object {
        @BindingAdapter(value = ["imageLink", "errorDrawable"], requireAll = false)
        @JvmStatic
        fun putImgInto(imgView: ImageView, link: String?, drawable: Drawable?) {
            var _drawable = drawable
            val progress = CircularProgressDrawable(imgView.context)
            progress.strokeWidth = 5f
            progress.centerRadius = 30f
            progress.start()
            if (drawable == null) _drawable =
                ContextCompat.getDrawable(imgView.context, R.mipmap.ic_launcher)
            Glide
                .with(imgView)
                .load(link)
                .placeholder(progress)
                .timeout(10000)
                .dontAnimate()
                .error(_drawable)
                .priority(Priority.HIGH)
                .into(imgView)
        }
    }

}