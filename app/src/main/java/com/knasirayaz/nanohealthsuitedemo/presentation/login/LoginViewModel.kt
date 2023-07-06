package com.knasirayaz.nanohealthsuitedemo.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knasirayaz.nanohealthsuitedemo.domain.common.CredentialValidator
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse
import com.knasirayaz.nanohealthsuitedemo.domain.usecase.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var loginCase: Login,
    private var credentialValidator: CredentialValidator
) : ViewModel() {

    private val loginObserver = MutableLiveData<ResultStates<LoginResponse>>()

    fun getLoginObserver(): LiveData<ResultStates<LoginResponse>> {
        return loginObserver
    }

    fun doLogin(hashMap: HashMap<String, String>) {
        val validation = fieldsAreValid(hashMap)
        if (validation.isValid)
            viewModelScope.launch {
                loginObserver.value = ResultStates.Loading(true)
                loginObserver.postValue(loginCase.invoke(hashMap))
                loginObserver.value = ResultStates.Loading(false)
            }
        else
            loginObserver.postValue(validation.errorMessage?.let { ResultStates.ValidationFailed(it) })
    }

    private fun fieldsAreValid(hashMap: HashMap<String, String>): CredentialValidator.ValidationState {
        val usernameValidation = credentialValidator.isValidUserName(hashMap["username"].toString())
        val passwordValidation = credentialValidator.isPasswordValid(hashMap["password"].toString())

        if (!usernameValidation.isValid) {
            return usernameValidation
        }

        if (!passwordValidation.isValid) {
            return passwordValidation
        }

        return CredentialValidator.ValidationState(true, null)
    }
}