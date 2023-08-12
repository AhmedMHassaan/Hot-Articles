package com.ahmedmhassaan.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val description: String,
    val author: String?,
    val url: String,
    val source: String,
    val content: String,
    val date: Long,
    val image: String,
    val isInFav: Boolean
) : Serializable {

}
