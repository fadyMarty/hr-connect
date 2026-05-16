package com.hrconnect.android.presentation.assistant

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.android.data.util.AssistantResult
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
            assistantRepository.init()
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
            assistantRepository.generateStream(
                prompt = state.value.messageState.text.toString()
            ).collect { result ->
                when (result) {
                    is AssistantResult.Delta -> {
                        val message = state.value.messages.first()
                        _state.update {
                            it.copy(
                                messages = it.messages.toMutableList().apply {
                                    set(
                                        index = 0,
                                        element = message.copy(
                                            content = message.content + result.text
                                        )
                                    )
                                }
                            )
                        }
                    }
                    is AssistantResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}