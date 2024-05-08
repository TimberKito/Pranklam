package com.rinbows.soft.pranklam.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rinbows.soft.pranklam.tools.AppConstant

@Entity(tableName = AppConstant.TABLE_NAME_SOUNDS)
data class DataListDTO(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val classId: String? = null,
    var isCollect: Boolean = false,
    var downloadUrl: String? = null,

    val mp3Url: String,
    val preUrl: String,
    val title: String
) {}