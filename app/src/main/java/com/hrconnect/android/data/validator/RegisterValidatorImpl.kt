package com.hrconnect.android.data.validator

import com.hrconnect.android.domain.validator.RegisterValidator

/**
 * Имплементация валидатора регистрации.
 *
 * Дата создания: 26-05-2026.
 * Автор создания: 1.
 */
class RegisterValidatorImpl : RegisterValidator {

    /**
     * Функция для валидации Email
     *
     * @param email электронная почта
     *
     * @return [Boolean] результат валидации
     */
    override fun validateEmail(email: String): Boolean {
        val emailPattern = "[a-z0-9]+@[a-z0-9]+\\.[a-z]+"
        return emailPattern.toRegex().matches(email)
    }

    /**
     * Функция для валидации пароля
     *
     * @param password пароль
     *
     * @return [Boolean] результат валидации
     */
    override fun validatePassword(password: String): Boolean {
        return password.length >= 8
    }

    /**
     * Функнция для подтверждения пароля
     *
     * @param password пароль
     * @param confirmPassword подтверждение пароля
     *
     * @return [Boolean] результат валидации
     */
    override fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}