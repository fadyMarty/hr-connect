package com.hrconnect.android.presentation.assistant

sealed interface AssistantEvent {
    data object OnCloseClick : AssistantEvent
    data object OnSendMessageClick : AssistantEvent
}