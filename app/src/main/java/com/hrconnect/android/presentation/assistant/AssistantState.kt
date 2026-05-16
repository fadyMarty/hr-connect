package com.hrconnect.android.presentation.assistant

import androidx.compose.foundation.text.input.TextFieldState
import com.hrconnect.android.domain.model.Message

data class AssistantState(
    val isLoading: Boolean = false,
    val messageState: TextFieldState = TextFieldState(),
    val messages: List<Message> = emptyList(),
    val error: String? = null,
)