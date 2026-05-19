package com.hrconnect.android.presentation.login

sealed interface LoginEvent {
    data object OnTogglePasswordVisibility : LoginEvent
    data class OnRememberUserChecked(val rememberUser: Boolean) : LoginEvent
    data object OnLoginClick : LoginEvent
    data object OnSuccess : LoginEvent
}