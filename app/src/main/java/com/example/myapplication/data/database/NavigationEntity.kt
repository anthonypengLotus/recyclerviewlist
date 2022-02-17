package com.example.myapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "navigation")
data class NavigationEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    val focusColor: String,
    val api: String,
    val key: String,

)