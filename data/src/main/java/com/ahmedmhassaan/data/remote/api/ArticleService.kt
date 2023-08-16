package com.ahmedmhassaan.data.remote.api

import com.ahmedmhassaan.data.model.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("everything")
    suspend fun search(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") currentPage: Int,
//        @Query("from") : String,
//        @Query("") : String,
//        @Query("") : String,
    ): Result<ArticlesResponse>


    @GET("top-headlines")
    suspend fun topHeadLines(
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("country")country: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") currentPage: Int,
//        @Query("from") : String,
//        @Query("") : String,
//        @Query("") : String,
    ): Result<ArticlesResponse>


}