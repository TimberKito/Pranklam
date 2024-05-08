package com.rinbows.soft.pranklam.tools

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import com.rinbows.soft.pranklam.App
import com.rinbows.soft.pranklam.data.CategoryDTO
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

object DataTools {

    fun parseJsonFile(jsonInputStream: InputStream): List<CategoryDTO> {
        val reader = InputStreamReader(jsonInputStream)
        val jsonString = reader.readText()
        return Gson().fromJson(jsonString, Array<CategoryDTO>::class.java).toList()
    }

    fun getCategoryList(): List<CategoryDTO> {
        return parseJsonFile(App.appContext.assets.open("prank.json"))
    }

    fun getDataMp3(context: Context, url: String, downloadCall: (File?) -> Unit) {
        Glide.with(context).downloadOnly().load(url).addListener(object : RequestListener<File> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<File>,
                isFirstResource: Boolean
            ): Boolean {
                downloadCall.invoke(null)
                return false
            }

            override fun onResourceReady(
                resource: File,
                model: Any,
                target: Target<File>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                downloadCall.invoke(resource)
                return false
            }
        }).preload()
    }
}