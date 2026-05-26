package com.hrconnect.android.presentation.register

import androidx.annotation.StringRes
import androidx.compose.foundation.text.input.TextFieldState

/**
 * Состояние экрана регистрации.
 *
 * Дата создания: 26-05-2026.
 * Автор создания: 1.
 */
data class RegisterState(
    val firstNameState: TextFieldState = TextFieldState(),
    val isFirstNameValid: Boolean = true,
    val lastNameState: TextFieldState = TextFieldState(),
    val isLastNameValid: Boolean = true,
    val emailState: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = true,
    val passwordState: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val isPasswordValid: Boolean = true,
    val confirmPasswordState: TextFieldState = TextFieldState(),
    val isConfirmPasswordVisible: Boolean = false,
    @StringRes val confirmPasswordError: Int? = null,
    val acceptedTerms: Boolean = false,
)