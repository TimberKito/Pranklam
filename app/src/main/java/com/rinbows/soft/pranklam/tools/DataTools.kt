package com.rinbows.soft.pranklam.tools

import com.google.gson.Gson
import com.rinbows.soft.pranklam.App
import com.rinbows.soft.pranklam.data.CategoryDTO
import java.io.InputStream
import java.io.InputStreamReader

object DataTools {

    fun parseJsonFile(jsonInputStream: InputStream): List<CategoryDTO> {
        val reader = InputStreamReader(jsonInputStream)
        val jsonString = reader.readText()
        return Gson().fromJson(jsonString, Array<CategoryDTO>::class.java).toList()
    }

    fun getCategoryList(): List<CategoryDTO> {
        return parseJsonFile(App.appContext.assets.open("resource.json"))
    }

}