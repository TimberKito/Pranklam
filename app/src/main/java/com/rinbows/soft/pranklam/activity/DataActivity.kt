package com.rinbows.soft.pranklam.activity

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.rinbows.soft.pranklam.adapter.DataAdapter
import com.rinbows.soft.pranklam.data.CategoryDTO
import com.rinbows.soft.pranklam.data.DataListDTO
import com.rinbows.soft.pranklam.databinding.ActivityDataBinding
import com.rinbows.soft.pranklam.listener.DetailsItemClickListener
import com.rinbows.soft.pranklam.tools.AppConstant

class DataActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDataBinding
    private lateinit var category: CategoryDTO
    override fun getActivityContentView(): View {
        binding = ActivityDataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        category = intent.getSerializableExtra(AppConstant.KEY_EXTRA) as CategoryDTO
        initButton()
        initItem()
        initTitle()
    }

    private fun initTitle() {
        binding.dataName.text = category.categoryName
    }

    private fun initButton() {
        binding.detailsBack.setOnClickListener(this)
    }

    private fun initItem() {
        val dataList = category.list
        binding.recyclerItem.run {
            layoutManager = GridLayoutManager(context, 3)

            adapter = DataAdapter(context, dataList, object : DetailsItemClickListener {
                override fun onItemClick(position: Int, dataListDTO: DataListDTO) {
                    val intent = Intent(context, PlayerActivity::class.java)
                    intent.putExtra(AppConstant.KEY_EXTRA, dataListDTO)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.detailsBack -> {
                finish()
            }
        }
    }
}