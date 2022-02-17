package com.example.myapplication.repository

import com.example.myapplication.data.database.NavigationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val playersDao: NavigationDao) {

    fun getPlayers() = playersDao.getList()
}