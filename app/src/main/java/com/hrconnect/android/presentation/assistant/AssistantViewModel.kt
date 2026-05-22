package com.hrconnect.android.presentation.assistant

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.android.domain.model.Message
import com.hrconnect.android.domain.repository.AssistantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssistantViewModel(
    private val assistantRepository: AssistantRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AssistantState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            assistantRepository.initialize()
        }
    }

    fun onEvent(event: AssistantEvent) {
        when (event) {
            AssistantEvent.OnSendMessageClick -> sendMessage()
            else -> Unit
        }
    }

    private fun sendMessage() {
        viewModelScope.launch {
            val userMessage = Message(
                content = state.value.messageState.text.toString(),
                isFromUser = true
            )
            val assistantMessage = Message(
                content = "",
                isFromUser = false
            )
            _state.update {
                it.copy(
                    isLoading = true,
                    messages = it.messages.toMutableList().apply {
                        add(0, userMessage)
                        add(0, assistantMessage)
                    }
                )
            }
            state.value.messageState.clearText()
            assistantRepository.sendMessage(userMessage.content).collect { token ->
                _state.update {
                    it.copy(
                        messages = it.messages.toMutableList().apply {
                            val message = first()
                            set(
                                index = 0,
                                element = message.copy(
                                    content = message.content + token
                                )
                            )
                        }
                    )
                }
            }
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        assistantRepository.close()
    }
}