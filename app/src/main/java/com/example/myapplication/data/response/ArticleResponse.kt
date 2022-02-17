package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(@field:SerializedName("id") val id:String,
                           @field:SerializedName("date_gmt") val date_gmt:String,
                           @field:SerializedName("title") val title:String,
                           @field:SerializedName("tag_indexed") val tag_indexed:String,
                           @field:SerializedName("link") val link:String,
//                           @field:SerializedName("toc") val toc:String,
                           @field:SerializedName("excerpt") val excerpt:String,
                           @field:SerializedName("type") val type:String,
                           @field:SerializedName("slug") val slug:String,
                           @field:SerializedName("status") val status:String,
                           @field:SerializedName("image") val image:String,
                           @field:SerializedName("image_color") val image_color:String,
                           @field:SerializedName("image_alternate") val image_alternate:String,
//                           @field:SerializedName("content_images") val content_images:String,
                           @field:SerializedName("web_view") val web_view:String,
//                           @field:SerializedName("author") val author:String,
//                           @field:SerializedName("reviewer") val reviewer:String,
//                           @field:SerializedName("category") val category:String,
                           @field:SerializedName("default_short_link") val default_short_link:String,
                           @field:SerializedName("topAppDownloadBanner_short_link") val topAppDownloadBanner_short_link:String,
                           @field:SerializedName("featured") val featured:Int,
                           @field:SerializedName("article_type") val article_type:String,
                           @field:SerializedName("api") val api:String,
                           @field:SerializedName("sticky_article") val sticky_article:String,
                           @field:SerializedName("design") val design:String,

)