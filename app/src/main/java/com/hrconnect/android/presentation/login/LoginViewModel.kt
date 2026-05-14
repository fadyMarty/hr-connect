package com.hrconnect.android.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnTogglePasswordVisibility -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }
            is LoginEvent.OnRememberUserChecked -> {
                _state.update {
                    it.copy(
                        rememberUser = event.rememberUser
                    )
                }
            }
            is LoginEvent.OnLoginClick -> login()
            else -> Unit
        }
    }

    private fun login() {
        if (!validateFormInputs()) {
            return
        }

        viewModelScope.launch {
            eventChannel.send(LoginEvent.Success)
        }
    }

    private fun validateFormInputs(): Boolean {
        val email = state.value.emailState.text.toString()
        val password = state.value.passwordState.text.toString()

        val isEmailValid = email.isNotBlank()
        val isPasswordValid = password.isNotBlank()

        _state.update {
            it.copy(
                isEmailValid = isEmailValid,
                isPasswordValid = isPasswordValid
            )
        }

        return isEmailValid && isPasswordValid
    }
}