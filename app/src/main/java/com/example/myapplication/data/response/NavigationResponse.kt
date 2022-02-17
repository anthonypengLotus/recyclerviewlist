package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName

data class NavigationResponse(@field:SerializedName("name") val name:String,
                              @field:SerializedName("focusColor") val focusColor:String,
                              @field:SerializedName("api") val api:String,
                              @field:SerializedName("key") val key:String,
                              )