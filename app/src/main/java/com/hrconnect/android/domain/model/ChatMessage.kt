package com.hrconnect.android.domain.model

data class ChatMessage(
    val content: String,
    val isFromUser: Boolean,
    val isLoading: Boolean = false,
    val error: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
)