package com.hrconnect.android.presentation.loading

sealed interface LoadingEvent {
    data object Success : LoadingEvent
}