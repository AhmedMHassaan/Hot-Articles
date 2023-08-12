package com.ahmedmhassaan.data.throwables

import com.google.gson.annotations.SerializedName

data class HostApiError(
    val status: String,
    val code: String,
    @SerializedName("message") val errorMessage: String
) : Throwable(errorMessage)