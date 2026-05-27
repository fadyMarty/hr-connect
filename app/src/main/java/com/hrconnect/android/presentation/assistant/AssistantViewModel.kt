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
            is AssistantEvent.OnRetryClick -> retry(event.index)
            else -> Unit
        }
    }

    private fun sendMessage() {
        val content = state.value.messageState.text.toString()
        if (content.isBlank()) {
            return
        }

        viewModelScope.launch {
            val userMessage = ChatMessage(
                content = content,
                isFromUser = true
            )
            val assistantMessage = ChatMessage(
                content = "",
                isFromUser = false
            )
            val messages = state.value.messages.toMutableList()
            messages.add(0, userMessage)
            messages.add(0, assistantMessage)
            _state.update {
                it.copy(
                    messages = messages
                )
            }
            state.value.messageState.clearText()

            assistantRepository.sendMessage(content)
                .catch { e ->
                    val updatedMessages = state.value.messages.toMutableList()
                    updatedMessages[0] = updatedMessages[0].copy(
                        error = e.message
                            ?: "Произошла ошибка при обработке вашего запроса. Пожалуйста, попробуйте еще раз."
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
        }
    }

    private fun retry(index: Int) {
        viewModelScope.launch {
            val messages = state.value.messages.toMutableList()
            messages[0] = messages[index].copy(
                error = null
            )
            _state.update {
                it.copy(
                    messages = messages
                )
            }
            val userMessage = state.value.messages[index + 1]
            assistantRepository.sendMessage(userMessage.content)
                .catch { e ->
                    val updatedMessages = state.value.messages.toMutableList()
                    updatedMessages[index] = updatedMessages[index].copy(
                        error = e.message
                            ?: "Произошла ошибка при обработке вашего запроса. Пожалуйста, попробуйте еще раз."
                    )
                    _state.update {
                        it.copy(
                            messages = updatedMessages
                        )
                    }
                }
                .collect { token ->
                    val updatedMessages = state.value.messages.toMutableList()
                    val currentMessage = updatedMessages[index]
                    updatedMessages[index] = currentMessage.copy(
                        content = currentMessage.content + token
                    )
                    _state.update {
                        it.copy(
                            messages = updatedMessages
                        )
                    }
                }
        }
    }
}