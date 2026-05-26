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
        TODO()
    }

    /**
     * Функция для валидации пароля
     *
     * @param password пароль
     *
     * @return [Boolean] результат валидации
     */
    override fun validatePassword(password: String): Boolean {
        TODO()
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
        TODO()
    }
}