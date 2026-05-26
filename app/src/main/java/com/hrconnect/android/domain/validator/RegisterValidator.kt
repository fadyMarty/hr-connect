package com.hrconnect.android.domain.validator

/**
 * Интерфейс валидатора регистрации.
 *
 * Дата создания: 26-05-2026.
 * Автор создания: 1.
 */
interface RegisterValidator {
    fun validateEmail(email: String): Boolean
    fun validatePassword(password: String): Boolean
    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean
}