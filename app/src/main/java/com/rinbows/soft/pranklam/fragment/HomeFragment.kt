package com.rinbows.soft.pranklam.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.rinbows.soft.pranklam.adapter.HomeAdapter
import com.rinbows.soft.pranklam.data.CategoryDTO
import com.rinbows.soft.pranklam.databinding.HomeFragmentBinding
import com.rinbows.soft.pranklam.listener.HomeClickListener
import com.rinbows.soft.pranklam.tools.DataTools

class HomeFragment : BaseFragment() {

    private lateinit var binding: HomeFragmentBinding

    override fun getFragmentContentView(): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        initVP()
    }

    private fun initVP() {

        val categoryList = DataTools.getCategoryList()

        binding.recyclerHome.run {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = HomeAdapter(requireContext(), categoryList, object : HomeClickListener {
                override fun onItemClick(position: Int, categoryDTO: CategoryDTO) {
//                        val intent = Intent(requireContext(), DetailsActivity::class.java)
//                        intent.putExtra("KEY_EXTRA", categoryModel)
//                        startActivity(intent)
                    Log.d("HomeOnClick", "item has been click!")
                }
            })
        }

    }
}