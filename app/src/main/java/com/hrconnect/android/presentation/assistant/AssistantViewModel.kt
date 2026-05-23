package com.hrconnect.android.presentation.assistant

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.android.domain.model.ChatMessage
import com.hrconnect.android.domain.repository.AssistantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssistantViewModel(
    private val assistantRepository: AssistantRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AssistantState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            assistantRepository.initialize()
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    fun onEvent(event: AssistantEvent) {
        when (event) {
            AssistantEvent.OnSendMessageClick -> sendMessage()
            else -> Unit
        }
    }

    private fun sendMessage() {
        val message = state.value.messageState.text.toString()
        if (message.isBlank()) {
            return
        }

        viewModelScope.launch {
            val messages = state.value.messages.toMutableList()
            val userMessage = ChatMessage(
                content = message,
                isFromUser = true
            )
            val assistantMessage = ChatMessage(
                content = "",
                isFromUser = false,
                isLoading = true
            )
            messages.add(0, userMessage)
            messages.add(0, assistantMessage)
            _state.update {
                it.copy(
                    messages = messages
                )
            }
            state.value.messageState.clearText()
            assistantRepository.sendMessage(message)
                .catch { e ->
                    val updatedMessages = state.value.messages.toMutableList()
                    updatedMessages[0] = updatedMessages[0].copy(
                        error = e.message
                    )
                    _state.update {
                        it.copy(
                            messages = updatedMessages
                        )
                    }
                }
                .collect { token ->
                    val updatedMessages = state.value.messages.toMutableList()
                    val currentMessage = updatedMessages[0]
                    updatedMessages[0] = currentMessage.copy(
                        content = currentMessage.content + token
                    )
                    _state.update {
                        it.copy(
                            messages = updatedMessages
                        )
                    }
                }
            val updatedMessages = state.value.messages.toMutableList()
            updatedMessages[0] = updatedMessages[0].copy(
                isLoading = false
            )
            _state.update {
                it.copy(
                    messages = updatedMessages
                )
            }
        }
    }
}