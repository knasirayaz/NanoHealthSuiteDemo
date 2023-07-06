package com.knasirayaz.nanohealthsuitedemo.domain.repository

import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse

interface LoginRepository {
    suspend fun doLogin(loginHash : HashMap<String, String>) : ResultStates<LoginResponse>
}