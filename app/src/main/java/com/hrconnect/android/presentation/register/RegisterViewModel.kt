package com.hrconnect.android.presentation.register

import androidx.lifecycle.ViewModel
import com.hrconnect.android.domain.validator.RegisterValidator
import com.hrconnect.netlib.auth.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val registerValidator: RegisterValidator,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        TODO()
    }
}