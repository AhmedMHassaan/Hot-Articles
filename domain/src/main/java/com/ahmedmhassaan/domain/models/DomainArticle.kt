package com.ahmedmhassaan.domain.models

import java.io.Serializable

data class DomainArticle(
    val source: DomainArticleSource,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
):Serializable


