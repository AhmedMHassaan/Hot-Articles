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
    return ArticleEntity(
        this.title,
        this.description,
        this.author,
        this.url,
        this.source.name,
        this.content,
        this.publishedAt,
        this.urlToImage,
        false
    )
}

fun DomainArticle.puplishingDate(): String {
    return try {
        val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
        val dateAndTime = formatter.parse(this.publishedAt)
        dateAndTime?.let {
            val justDate: String = formatter.format(dateAndTime)
            justDate
        } ?: this.publishedAt

    } catch (e: Exception) {
        return this.publishedAt
    }

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

fun Article.toEntityArticle(): ArticleEntity {
    return ArticleEntity(
        this.title,
        this.description,
        this.author,
        this.url,
        this.source.name,
        this.content,
        this.publishedAt,
        this.urlToImage,
        false,
        0
    )
}

fun Article.toEntityArticle(page: Int = 1, isInFav: Boolean = false): ArticleEntity {
    return ArticleEntity(
        this.title,
        this.description,
        this.author,
        this.url,
        this.source.name,
        this.content,
        this.publishedAt,
        this.urlToImage,
        isInFav,
        page
    )
}

fun ArticleEntity.toDomainArticle(): DomainArticle {

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