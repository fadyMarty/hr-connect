package com.hrconnect.android.domain.model

import java.time.LocalDateTime

data class Message(
    val content: String,
    val isFromUser: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)