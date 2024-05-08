package com.rinbows.soft.pranklam.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rinbows.soft.pranklam.App
import com.rinbows.soft.pranklam.tools.AppConstant


@Database(
    entities = [DataListDTO::class], version = AppConstant.DB_VERSION, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val dataBase: AppDatabase by lazy {
            getInstance()
        }

        private fun getInstance(): AppDatabase {
            return Room.databaseBuilder(
                App.appContext, AppDatabase::class.java, AppConstant.DB_NAME
            ).build()
        }
    }

    abstract fun getDataListDao(): DataListDao

}