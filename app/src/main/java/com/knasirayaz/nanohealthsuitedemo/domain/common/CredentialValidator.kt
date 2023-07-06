package com.knasirayaz.nanohealthsuitedemo.domain.common

import android.util.Patterns
import com.knasirayaz.nanohealthsuitedemo.R

object CredentialValidator {

    data class ValidationState(val isValid : Boolean, val errorMessage : Int?)

    fun isValidEmail(email: String): ValidationState {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return ValidationState(true, null)
        return if (email.trim().isEmpty()) {
            ValidationState(false, R.string.enter_email)
        } else
            ValidationState(false, R.string.invalid_email)
    }

    fun isValidUserName(userName: String): ValidationState {
        return if(userName.length >= 4){
            ValidationState(true, null)
        }else if (userName.trim().isEmpty()) {
            ValidationState(false, R.string.enter_username)
        } else
            ValidationState(false, R.string.invalid_username)
    }


    fun isPasswordValid(password : String): ValidationState {
       return if(password.length >= 4){
            ValidationState(true, null)
        }else if (password.trim().isEmpty()) {
            ValidationState(false, R.string.enter_password)
        } else
            ValidationState(false, R.string.invalid_password)
    }


}
