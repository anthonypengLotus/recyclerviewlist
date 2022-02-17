package com.example.myapplication.data.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataResponse(@field:SerializedName("d") val d:DataDetailResponse)