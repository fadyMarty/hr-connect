package com.hrconnect.android.presentation.register

import androidx.compose.foundation.text.input.TextFieldState

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
    val isConfirmPasswordValid: Boolean = true,
    val isTermsAccepted: Boolean = false,
)