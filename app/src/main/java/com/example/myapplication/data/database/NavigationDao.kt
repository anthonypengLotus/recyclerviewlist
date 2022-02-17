package com.example.myapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NavigationDao {
    @Query("SELECT * FROM navigation")
    fun getList(): Flow<List<NavigationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(teams: List<NavigationEntity>)

    @Query("DELETE FROM navigation")
    suspend fun deleteList()
}