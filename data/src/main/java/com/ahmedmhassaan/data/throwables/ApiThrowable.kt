package com.ahmedmhassaan.data.throwables

import com.ahmedmhassaan.data.model.ApiError


data class ApiThrowable(
    val body: ApiError?,
    val code: Int
) : Throwable()
