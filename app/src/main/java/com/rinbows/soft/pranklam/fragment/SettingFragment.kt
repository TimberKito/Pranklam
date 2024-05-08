package com.rinbows.soft.pranklam.fragment

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.rinbows.soft.pranklam.R
import com.rinbows.soft.pranklam.databinding.SettingFragmentBinding

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
                Log.d("onclick", "has been click！")
            }
        }
    }
}