package com.rinbows.soft.pranklam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
@Dao
interface DataListDao {
    @Query("select * from sounds where isCollect = :collect ")
    suspend fun getCollectData(collect: Boolean = true): List<DataListDTO>

    @Query("update sounds set isCollect = 0")
    suspend fun deleteAllCollect()
    @Update
    suspend fun updateData(dataList: DataListDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(dataList: DataListDTO)

    @Delete
    suspend fun deleteData(dataList: DataListDTO)

}