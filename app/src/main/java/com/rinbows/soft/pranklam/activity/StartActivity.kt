package com.rinbows.soft.pranklam.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.applovin.mediation.MaxAd
import com.applovin.mediation.ads.MaxInterstitialAd
import com.rinbows.soft.pranklam.App
import com.rinbows.soft.pranklam.ad.AdListener
import com.rinbows.soft.pranklam.ad.AdManager
import com.rinbows.soft.pranklam.databinding.ActivityStartPageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class StartActivity : BaseActivity() {

    private lateinit var binding: ActivityStartPageBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val countTime: Long = 25000

    private lateinit var lists: List<MaxInterstitialAd>
    private lateinit var timer: CountDownTimer
    private var need_Show = true

    override fun getActivityContentView(): View {
        binding = ActivityStartPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {
        super.initView()
        timer = object : CountDownTimer(countTime, 500) {
            override fun onTick(millisUntilFinished: Long) {
                if (need_Show) {
                    startShowAd {}
                }
            }

            override fun onFinish() {
                if (need_Show) {
                    startShowAd {
                        startMainActivity()
                    }
                }
            }
        }
        startAd()
    }
    private fun startShowAd(action: () -> Unit) {
        val checkCacheAd = AdManager.onCache(lists)
        if (checkCacheAd == null) {
            action.invoke()
        } else {
            need_Show = false
            AdManager.setAdListener(checkCacheAd, object : AdListener {
                override fun onFail(ad: MaxAd?) {
                    startMainActivity()
                }

                override fun onSuccess() {

                }

                override fun onHidden() {
                    startMainActivity()
                }

            })
            checkCacheAd.showAd()
        }
    }
    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun startAd() {
        if (!App.initOK) {
            LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    loadMyAdAndStart()

                    Log.d("------------","------------1sucess")
                }
            }, IntentFilter(App.AD_INIT))
        } else {
            loadMyAdAndStart()
            Log.d("------------","------------2sucess")
        }
    }

    private fun loadMyAdAndStart() {
        lists = AdManager.AdLoad(this@StartActivity)
        timer.start()
    }
}