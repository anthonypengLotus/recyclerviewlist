package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName

data class DataDetailResponse(@field:SerializedName("navigation") val navigation: List<NavigationResponse>,
                              @field:SerializedName("article") val article:List<ArticleResponse>,
                              @field:SerializedName("index") val index :Int,
                              @field:SerializedName("new_contest") val new_contest:Int
                                  )