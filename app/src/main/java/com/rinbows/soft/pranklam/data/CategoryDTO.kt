package com.rinbows.soft.pranklam.data

import java.io.Serializable

data class CategoryDTO(
    val categoryId: String, val categoryName: String, val categoryUrl: String, val list: List<DataListDTO>
) : Serializable