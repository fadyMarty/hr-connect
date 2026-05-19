package com.hrconnect.android.domain.validator

interface RegisterValidator {
    fun validateEmail(email: String): Boolean
    fun validatePassword(password: String): Boolean
    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean
}