package com.demo.demoapplication.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.demo.demoapplication.R
import com.demo.demoapplication.koin.appModule
import com.demo.demoapplication.utils.Constants
import com.demo.demoapplication.utils.PreferenceUtils
import com.demo.demoapplication.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.startKoin

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            startKoin(applicationContext, listOf(appModule))

            delay(Constants().SPLASH_SCREEN_TIME_OUT.toLong())
            if(PreferenceUtils().isUserLogin(this@SplashActivity)){
                Utils().launchMainActivity(this@SplashActivity, MainActivity::class.java)
            }else{
                Utils().launchMainActivity(this@SplashActivity, LoginActivity::class.java)
            }
        }

    }
}