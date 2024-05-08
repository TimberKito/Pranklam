package com.rinbows.soft.pranklam.fragment

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.rinbows.soft.pranklam.R
import com.rinbows.soft.pranklam.data.AppDatabase
import com.rinbows.soft.pranklam.databinding.SettingFragmentBinding
import com.rinbows.soft.pranklam.tools.AppConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: SettingFragmentBinding
    override fun getFragmentContentView(): View {
        binding = SettingFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        initTab()
    }

    private fun initTab() {
        binding.layoutRating.setOnClickListener(this)
        binding.layoutShare.setOnClickListener(this)
        binding.setLayoutDelete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.layoutRating -> {
                val url = getString(R.string.link) + (activity?.packageName ?: "")
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(url))
                startActivity(intent)
            }

            binding.layoutShare -> {
                val url = getString(R.string.link) + (activity?.packageName ?: "")
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT, url)
                startActivity(intent)
            }

            binding.setLayoutDelete -> {
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.dataBase.getDataListDao().deleteAllCollect()
                }
                sendDatabaseUpdatedBroadcast()
                Toast.makeText(
                    requireActivity(), "Cleared all collections successfully.", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun sendDatabaseUpdatedBroadcast() {
        val intent = Intent(AppConstant.ACTION_DATABASE_UPDATED)
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }
}