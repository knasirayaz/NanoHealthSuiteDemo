package com.knasirayaz.nanohealthsuitedemo.domain.usecase

import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse
import com.knasirayaz.nanohealthsuitedemo.domain.repository.LoginRepository
import javax.inject.Inject

class Login @Inject constructor(private var loginRepository: LoginRepository) {
    suspend operator fun invoke(loginHash : HashMap<String, String>): ResultStates<LoginResponse> {
        return loginRepository.doLogin(loginHash)
    }
}