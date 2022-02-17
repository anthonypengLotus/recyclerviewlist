package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [NavigationEntity::class], version = 1, exportSchema = false)
abstract class NavigationDatabase: RoomDatabase() {
    abstract fun navigationDao():NavigationDao

    companion object{
        @Volatile
        private var instance:NavigationDatabase?=null

        fun getInstance(context: Context):NavigationDatabase{
            return instance?: synchronized(this){
                instance?:buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): NavigationDatabase {
            return Room.databaseBuilder(context, NavigationDatabase::class.java, "navigation").build()
        }
    }
}