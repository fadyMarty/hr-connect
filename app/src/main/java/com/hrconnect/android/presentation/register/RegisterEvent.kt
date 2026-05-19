package com.hrconnect.android.presentation.register

sealed interface RegisterEvent {
    data object OnTogglePasswordVisibility : RegisterEvent
    data object OnToggleConfirmPasswordVisibility : RegisterEvent
    data object OnRegisterClick : RegisterEvent
    data class OnAcceptTerms(val isAccepted: Boolean) : RegisterEvent
    data object OnLoginClick : RegisterEvent
    data object OnSuccess : RegisterEvent
    data class OnError(val message: String) : RegisterEvent
}