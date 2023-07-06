package com.knasirayaz.nanohealthsuitedemo.domain.common

import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse
import javax.inject.Inject

class SessionManager @Inject constructor(private val appPreferences: AppPreferences) {
    fun saveSession(results: LoginResponse) {
        appPreferences.putString("token", results.token.toString())
    }

    fun getSession() : String {
        return appPreferences.getString("token").toString()
    }
}