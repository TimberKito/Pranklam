package com.rinbows.soft.pranklam

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkInitializationConfiguration

class App : Application() {
    companion object {
        lateinit var appContext: Context
        const val ADSDK = "VNL26CMGzXqLLfS5kMl2Nmz51a6LsHeLK3piaHf4dtiplx5ludvxFe6tU9hdhFWJEPLgH7QyzOpbXT056xw_Bi"
        const val AD_INIT = "on_success_action"
        var initOK = false
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initSDK()
    }


    private fun initSDK() {
        val initConfig =
            AppLovinSdkInitializationConfiguration.builder(ADSDK, this)
                .setMediationProvider(AppLovinMediationProvider.MAX)
                .build()
        AppLovinSdk.getInstance(this).initialize(initConfig) {
            initOK = true
            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(AD_INIT))
        }
        AppLovinSdk.getInstance(this).settings.setVerboseLogging(true)
    }
}