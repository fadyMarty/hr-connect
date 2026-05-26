package com.hrconnect.android.presentation.register

/**
 * События на экране регистрации.
 *
 * Дата создания: 26-05-2026.
 * Автор создания: 1.
 */
sealed interface RegisterEvent {
    data class OnError(val message: String) : RegisterEvent
    data object OnRegisterSuccess : RegisterEvent
}