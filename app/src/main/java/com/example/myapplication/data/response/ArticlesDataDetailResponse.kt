package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName

data class ArticlesDataDetailResponse(@field:SerializedName("article") val article:List<ArticleResponse>)