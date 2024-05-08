package com.rinbows.soft.pranklam.activity

import android.content.Intent
import android.view.View
import com.rinbows.soft.pranklam.databinding.ActivityStartPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : BaseActivity() {

    private lateinit var binding: ActivityStartPageBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val countTime: Long = 2000
    override fun getActivityContentView(): View {
        binding = ActivityStartPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        coroutineScope.launch {
            delay(countTime)
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}