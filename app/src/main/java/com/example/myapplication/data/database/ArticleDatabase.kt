package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun articleDao():ArticleDao

    companion object{
        @Volatile
        private var instance:ArticleDatabase?=null

        fun getInstance(context: Context):ArticleDatabase{
            return instance?: synchronized(this){
                instance?:buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): ArticleDatabase {
            return Room.databaseBuilder(context, ArticleDatabase::class.java, "article").build()
        }
    }
}