package com.demo.demoapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.demoapplication.R
import com.demo.demoapplication.utils.PreferenceUtils
import com.demo.demoapplication.utils.Utils
import com.demo.demoapplication.utils.ValidationUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var preferenceUtils :PreferenceUtils? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        preferenceUtils = PreferenceUtils()
        btnLogin.setOnClickListener {
            if(ValidationUtils().isValidEmail(edtEmail)&&ValidationUtils().isValidPassword(edtPassword)){
                preferenceUtils?.setEmail(this,edtEmail.text.toString())
                preferenceUtils?.setPassword(this,edtPassword.toString())
                preferenceUtils?.setUserLogin(this,true)
                Utils().launchMainActivity(this@LoginActivity,MainActivity::class.java)
            }
        }
    }
}