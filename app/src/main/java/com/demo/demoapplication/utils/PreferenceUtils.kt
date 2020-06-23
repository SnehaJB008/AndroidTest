package com.demo.demoapplication.utils

import android.content.Context

class PreferenceUtils {

    private fun putBooleanInPreferences(
        context: Context,
        value: Boolean, key: String
    ): Boolean {
        val sharedPreferences = context
            .getSharedPreferences(
                Constants().PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
        return true
    }
    fun putStringInPreferences(
        context: Context,
        value: String?, key: String?
    ): Boolean {
        val sharedPreferences = context
            .getSharedPreferences(
                Constants().PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
        return true
    }

    private fun getBooleanFromPreferences(
        context: Context,
        defaultValue: Boolean, key: String
    ): Boolean {
        val sharedPreferences = context
            .getSharedPreferences(
                Constants().PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun getStringFromPreferences(
        context: Context,
        defaultValue: String?, key: String?
    ): String? {
        val sharedPreferences = context
            .getSharedPreferences(
                Constants().PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        return sharedPreferences.getString(key, defaultValue)
    }

    fun isUserLogin(context: Context): Boolean {
        return getBooleanFromPreferences(
            context, false,
            Constants().IS_USER_LOGIN
        )
    }

    fun setUserLogin(
        context: Context,
        isLogin: Boolean
    ) {
        putBooleanInPreferences(
            context, isLogin,
            Constants().IS_USER_LOGIN
        )
    }

    //retrieve email address
    fun getEmail(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_EMAIL
        )
    }

    //save email address
    fun setEmail(
        context: Context,
        email: String
    ) {
        putStringInPreferences(context, email, Constants().USER_EMAIL)
    }
    //retrieve pass
    fun getPassword(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_PASSWORD
        )
    }

    //save pass
    fun setPassword(
        context: Context,
        password: String
    ) {
        putStringInPreferences(context, password, Constants().USER_PASSWORD)
    }
    //retrieve Image
    fun getProfileImage(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().PROFILE_IMAGE
        )
    }

    //save pass
    fun setProfileImage(
        context: Context,
        imageuri: String
    ) {
        putStringInPreferences(context, imageuri, Constants().PROFILE_IMAGE)
    }

}