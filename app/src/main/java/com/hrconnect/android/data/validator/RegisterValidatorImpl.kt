package com.hrconnect.android.data.validator

import com.hrconnect.android.domain.validator.RegisterValidator

class RegisterValidatorImpl : RegisterValidator {

    companion object {
        private const val EMAIL_PATTERN = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]+$"
        private const val MIN_PASSWORD_LENGTH = 8
    }

    override fun validateEmail(email: String): Boolean {

        return EMAIL_PATTERN.toRegex().matches(email)
    }

    override fun validatePassword(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }

    override fun validateConfirmPassword(
        password: String,
        confirmPassword: String,
    ): Boolean {
        return password == confirmPassword
    }
}