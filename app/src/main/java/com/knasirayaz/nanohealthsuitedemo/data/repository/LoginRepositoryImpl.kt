package com.knasirayaz.nanohealthsuitedemo.data.repository

import com.knasirayaz.nanohealthsuitedemo.data.source.remote.Webservice
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.common.SessionManager
import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse
import com.knasirayaz.nanohealthsuitedemo.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor (
    private val webService: Webservice,
    private val sessionManager: SessionManager
) : LoginRepository {
    override suspend fun doLogin(loginHash: HashMap<String, String>): ResultStates<LoginResponse> =
        withContext(Dispatchers.IO) {
            try {
                val results = webService.login(loginHash)
                if (!results.token.isNullOrEmpty()) {
                    sessionManager.saveSession(results)
                    return@withContext ResultStates.Success(results)
                } else
                    return@withContext ResultStates.Failed("Something went wrong")
            } catch (e: UnknownHostException){
                return@withContext ResultStates.Failed(e.message.toString())
            } catch (e : HttpException){
                return@withContext ResultStates.Failed(e.response()?.errorBody()?.string() ?: e.message.toString())
            }
        }
}