package com.demo

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.demo.demoapplication.koin.appModule
import org.koin.android.ext.android.startKoin

class Application : MultiDexApplication() {
    private var instance: Application? = null
    private var mContext: Context? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        mContext = this
        startKoin(applicationContext, listOf(appModule))
    }
}