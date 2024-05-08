package com.rinbows.soft.pranklam.listener

import com.rinbows.soft.pranklam.data.CategoryDTO

interface HomeClickListener {
    fun onItemClick(position: Int, categoryDTO: CategoryDTO)
}