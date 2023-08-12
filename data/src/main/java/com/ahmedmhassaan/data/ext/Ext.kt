package com.ahmedmhassaan.data.ext

import com.ahmedmhassaan.data.local.model.ArticleEntity
import com.ahmedmhassaan.data.model.Article
import com.ahmedmhassaan.data.model.ArticleSource
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.domain.models.DomainArticleSource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun DomainArticle.toDataArticle(): Article {
    return Article(
        ArticleSource(this.source.id, this.source.name),
        this.author,
        this.title,
        this.description,
        this.url,
        this.urlToImage,
        this.publishedAt,
        this.content
    )
}

fun DomainArticle.toEntityArticle(): ArticleEntity {
    val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
    val date = formatter.parse(this.publishedAt)
    return ArticleEntity(
        null,
        this.title,
        this.description,
        this.author,
        this.url,
        this.source.name,
        this.content,
        date?.time ?: -1,
        this.urlToImage,
        false
    )
}

fun Article.toDomainArticle(): DomainArticle {
    return DomainArticle(
        DomainArticleSource(this.source.id, this.source.name),
        this.author,
        this.title,
        this.description,
        this.url,
        this.urlToImage,
        this.publishedAt,
        this.content
    )
}

fun ArticleEntity.toDomainArticle(): DomainArticle {
    val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
    val date = formatter.format(Date(this.date))

    return DomainArticle(
        DomainArticleSource(null, this.source),
        this.author,
        this.title,
        this.description,
        this.url,
        this.image,
        date,
        this.content
    )
}