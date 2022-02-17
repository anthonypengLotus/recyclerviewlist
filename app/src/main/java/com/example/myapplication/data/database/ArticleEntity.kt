package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val title: String,
    val tag_indexed: String,
    val link: String,
    val slug: String,
    val image: String,
    val image_color: String,
    val web_view: String,
    val api: String,
)