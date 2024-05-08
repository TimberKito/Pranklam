package com.rinbows.soft.pranklam.fragment

import android.view.View
import com.rinbows.soft.pranklam.databinding.CollectFragmentBinding

class CollectFragment : BaseFragment() {
    private lateinit var binding: CollectFragmentBinding
    override fun getFragmentContentView(): View {
        binding = CollectFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
    }
}