package com.ahmedmhassaan.orangetask.utils

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.ahmedmhassaan.orangetask.R
import es.dmoral.toasty.Toasty

object ToastMessage {

    fun msg(context: Context, messages: String?, toastTag: ToastTags) {
        Toasty.Config.getInstance()
            .allowQueue(false)
            .setToastTypeface(ResourcesCompat.getFont(context, R.font.font1)!!)
            .setTextSize(context.resources.getDimension(com.intuit.sdp.R.dimen._4sdp).toInt())
            .supportDarkTheme(true)
            .tintIcon(true)
            .apply()




        when (toastTag) {
            ToastTags.SUCCRSS -> Toasty.success(
                context, messages.toString(), Toasty.LENGTH_SHORT
            ).show()

            ToastTags.ERROR -> Toasty.error(
                context, messages.toString(), Toasty.LENGTH_SHORT
            ).show()
        }
    }

    fun error(context: Context, messages: String) {
        msg(context, messages, ToastTags.ERROR)
    }

    fun success(context: Context?, msg: String) {
        context?.let {
            msg(context, msg, ToastTags.SUCCRSS)
        } ?: kotlin.run {
            error("Context is  null ")
        }

    }


    enum class ToastTags {
        SUCCRSS,
        ERROR
    }
}