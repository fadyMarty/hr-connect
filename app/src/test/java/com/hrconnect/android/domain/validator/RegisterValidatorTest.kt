package com.hrconnect.android.domain.validator

import com.google.common.truth.Truth.assertThat
import com.hrconnect.android.data.validator.RegisterValidatorImpl
import org.junit.Before
import org.junit.Test

class RegisterValidatorTest {

    private lateinit var validator: RegisterValidator

    @Before
    fun setUp() {
        validator = RegisterValidatorImpl()
    }

    @Test
    fun `корректный формат Email возвращает true`() {
        val isEmailValid = validator.validateEmail("name@domain.ru")
        assertThat(isEmailValid).isTrue()
    }

    @Test
    fun `некорретный формат Email возвращает false`() {
        val isEmailValid = validator.validateEmail("NAME@DOMAIN.ru")
        assertThat(isEmailValid).isFalse()
    }

    @Test
    fun `пароль длиной более 8 символов возвращает true`() {
        val isPasswordValid = validator.validatePassword("12345678")
        assertThat(isPasswordValid).isTrue()
    }

    @Test
    fun `пароль длиной менее 8 символов возвращает false`() {
        val isPasswordValid = validator.validatePassword("123")
        assertThat(isPasswordValid).isFalse()
    }

    @Test
    fun `совпадение пароля и подтверждения пароля возвращает true`() {
        val isConfirmPasswordValid = validator.validateConfirmPassword(
            password = "12345678",
            confirmPassword = "12345678"
        )
        assertThat(isConfirmPasswordValid).isTrue()
    }

    @Test
    fun `несовпадение пароля и подтверждения пароля возвращает false`() {
        val isConfirmPasswordValid = validator.validateConfirmPassword(
            password = "12345678",
            confirmPassword = "87654321"
        )
        assertThat(isConfirmPasswordValid).isFalse()
    }
}