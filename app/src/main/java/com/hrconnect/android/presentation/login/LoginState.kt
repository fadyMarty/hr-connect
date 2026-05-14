package com.hrconnect.android.presentation.login

import androidx.compose.foundation.text.input.TextFieldState

data class LoginState(
    val emailState: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = true,
    val passwordState: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val isPasswordValid: Boolean = true,
    val rememberUser: Boolean = false,
)
