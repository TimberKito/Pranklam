package com.rinbows.soft.pranklam.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.rinbows.soft.pranklam.adapter.MainAdapter
import com.rinbows.soft.pranklam.databinding.ActivityMainBinding
import com.rinbows.soft.pranklam.fragment.CollectFragment
import com.rinbows.soft.pranklam.fragment.HomeFragment
import com.rinbows.soft.pranklam.fragment.SettingFragment

class MainActivity : BaseActivity(), OnPageChangeListener, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var viewPagerFragments = mutableListOf<Fragment>()

    override fun getActivityContentView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        initViewPager()
        initTab()
    }

    private fun initViewPager() {
        viewPagerFragments.add(HomeFragment())
        viewPagerFragments.add(CollectFragment())
        viewPagerFragments.add(SettingFragment())
        binding.mainViewpager.run {
            adapter = MainAdapter(viewPagerFragments, supportFragmentManager)
            addOnPageChangeListener(this@MainActivity)
            setOffscreenPageLimit(0)
        }
        setTabSelect(0)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        setTabSelect(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.mainTabHome -> {
                setTabSelect(0)
            }

            binding.mainTabCollect -> {
                setTabSelect(1)
            }

            binding.mainTabSet -> {
                setTabSelect(2)
            }
        }
    }

    private fun setTabSelect(position: Int) {
        when (position) {
            0 -> {
                binding.mainTabHome.isSelected = true
                binding.mainTabCollect.isSelected = false
                binding.mainTabSet.isSelected = false
                binding.mainViewpager.currentItem = 0
            }

            1 -> {
                binding.mainTabHome.isSelected = false
                binding.mainTabCollect.isSelected = true
                binding.mainTabSet.isSelected = false
                binding.mainViewpager.currentItem = 1
            }

            2 -> {
                binding.mainTabHome.isSelected = false
                binding.mainTabCollect.isSelected = false
                binding.mainTabSet.isSelected = true
                binding.mainViewpager.currentItem = 2
            }
        }
    }

    private fun initTab() {
        binding.mainTabHome.setOnClickListener(this)
        binding.mainTabCollect.setOnClickListener(this)
        binding.mainTabSet.setOnClickListener(this)
    }
}