package com.rinbows.soft.pranklam.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rinbows.soft.pranklam.adapter.CollectAdapter
import com.rinbows.soft.pranklam.data.CollectViewModel
import com.rinbows.soft.pranklam.databinding.CollectFragmentBinding
import com.rinbows.soft.pranklam.tools.AppConstant

class CollectFragment : BaseFragment() {
    private lateinit var binding: CollectFragmentBinding
    private lateinit var viewModel: CollectViewModel
    private lateinit var collectAdapter: CollectAdapter
    override fun getFragmentContentView(): View {
        binding = CollectFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filter = IntentFilter(AppConstant.ACTION_DATABASE_UPDATED)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(mDatabaseUpdatedReceiver, filter)
    }

    override fun initView() {
        super.initView()
        initItem()
    }

    private fun initItem() {
        collectAdapter = CollectAdapter(requireContext())
        binding.recyclerLike.run {
            adapter = collectAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.update()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[CollectViewModel::class.java]
        viewModel.getList().observe(viewLifecycleOwner, Observer {
            binding.collectCard.isVisible = it.isEmpty()
            collectAdapter.updateData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(mDatabaseUpdatedReceiver)
    }

    private val mDatabaseUpdatedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                if (intent.action.equals(AppConstant.ACTION_DATABASE_UPDATED)) {
                    viewModel.update()
                }
            }
        }
    }
}