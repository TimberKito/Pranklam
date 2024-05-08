package com.rinbows.soft.pranklam.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainAdapter(
    private val pagerData: List<Fragment>,
    private val fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return pagerData.size
    }

    override fun getItem(position: Int): Fragment {
        return pagerData[position]
    }
}