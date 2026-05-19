package com.hrconnect.android.data.validator

import com.hrconnect.android.domain.validator.RegisterValidator

class RegisterValidatorImpl : RegisterValidator {

    override fun validateEmail(email: String): Boolean {
        val emailPattern = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]+$"
        return emailPattern.toRegex().matches(email)
    }

    override fun validatePassword(password: String): Boolean {
        return password.length >= 8
    }

    override fun validateConfirmPassword(
        password: String,
        confirmPassword: String,
    ): Boolean {
        return password == confirmPassword
    }
}