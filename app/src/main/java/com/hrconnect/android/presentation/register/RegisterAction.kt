package com.hrconnect.android.presentation.register

/**
 * Действия на экране регистрации.
 *
 * Дата создания: 26-05-2026.
 * Автор создания: 1.
 */
sealed interface RegisterAction {
    data object OnTogglePasswordVisibility : RegisterAction
    data object OnToggleConfirmPasswordVisibility : RegisterAction
    data class OnAcceptTerms(val isAccepted: Boolean) : RegisterAction
    data object OnRegisterClick : RegisterAction
    data object OnLoginClick : RegisterAction
}