package com.hrconnect.android.domain.model

data class ChatMessage(
    val content: String,
    val isFromUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val error: String? = null,
)