package com.hrconnect.android.presentation.register

import androidx.annotation.StringRes
import androidx.compose.foundation.text.input.TextFieldState

data class RegisterState(
    val firstNameState: TextFieldState = TextFieldState(),
    val isFirstNameValid: Boolean = true,
    val lastNameState: TextFieldState = TextFieldState(),
    val isLastNameValid: Boolean = true,
    val emailState: TextFieldState = TextFieldState(),
    @StringRes val emailError: Int? = null,
    val passwordState: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    @StringRes val passwordError: Int? = null,
    val confirmPasswordState: TextFieldState = TextFieldState(),
    val isConfirmPasswordVisible: Boolean = false,
    @StringRes val confirmPasswordError: Int? = null,
    val acceptedTerms: Boolean = false,
)