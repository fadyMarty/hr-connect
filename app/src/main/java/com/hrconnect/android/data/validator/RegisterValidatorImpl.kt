package com.hrconnect.android.data.validator

import com.hrconnect.android.domain.validator.RegisterValidator

class RegisterValidatorImpl : RegisterValidator {

    override fun validateEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun validatePassword(password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun validateConfirmPassword(
        password: String,
        confirmPassword: String,
    ): Boolean {
        TODO("Not yet implemented")
    }
}