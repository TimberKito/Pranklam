package com.rinbows.soft.pranklam.listener

import com.rinbows.soft.pranklam.data.DataListDTO

interface DetailsItemClickListener {
    fun onItemClick(position: Int, dataListDTO: DataListDTO)
}