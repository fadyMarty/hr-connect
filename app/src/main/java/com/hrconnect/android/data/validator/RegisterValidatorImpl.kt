package com.hrconnect.android.data.validator

import com.hrconnect.android.domain.validator.RegisterValidator

/**
 * Имплементация валидатора регистрации.
 *
 * Дата создания: 26-05-2026.
 * Автор создания: 1.
 */
class RegisterValidatorImpl : RegisterValidator {

    companion object {
        private const val EMAIL_PATTERN = "[a-z0-9]+@[a-z0-9]+\\.[a-z]+"
        private const val MIN_PASSWORD_LENGTH = 8
    }

    /**
     * Функция для валидации Email
     *
     * @param email электронная почта
     *
     * @return [Boolean] результат валидации
     */
    override fun validateEmail(email: String): Boolean {
        return EMAIL_PATTERN.toRegex().matches(email)
    }

    /**
     * Функция для валидации пароля
     *
     * @param password пароль
     *
     * @return [Boolean] результат валидации
     */
    override fun validatePassword(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
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