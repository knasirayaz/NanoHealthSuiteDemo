package com.knasirayaz.nanohealthsuitedemo.domain.common

sealed class ResultStates<out T> {
    data class Success<out R>(val data: R) : ResultStates<R>()
    data class Loading(val isLoading: Boolean) : ResultStates<Nothing>()
    data class Failed(val error: String) : ResultStates<Nothing>()
    data class ValidationFailed(val error: Int) : ResultStates<Nothing>()

}
