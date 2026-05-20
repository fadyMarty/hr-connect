package com.hrconnect.android.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.android.R
import com.hrconnect.android.domain.validator.RegisterValidator
import com.hrconnect.android.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val registerValidator: RegisterValidator,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.OnTogglePasswordVisibility -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }
            RegisterEvent.OnToggleConfirmPasswordVisibility -> {
                _state.update {
                    it.copy(
                        isConfirmPasswordVisible = !it.isConfirmPasswordVisible
                    )
                }
            }
            is RegisterEvent.OnAcceptTerms -> {
                _state.update {
                    it.copy(
                        acceptedTerms = event.isAccepted
                    )
                }
            }
            RegisterEvent.OnRegisterClick -> register()
            else -> Unit
        }
    }

    private fun register() {
        if (!validateFormInputs()) {
            return
        }

        viewModelScope.launch {
            authRepository.register()
                .onSuccess {
                    eventChannel.send(RegisterEvent.OnSuccess)
                }
                .onFailure { e ->
                    if (e is HttpException) {
                        eventChannel.send(RegisterEvent.OnError(e.message()))
                    }
                }
        }
    }

    private fun validateFormInputs(): Boolean {
        val currentState = state.value
        val email = currentState.emailState.text.toString()
        val password = currentState.passwordState.text.toString()
        val confirmPassword = currentState.confirmPasswordState.text.toString()

        val isEmailValid = registerValidator.validateEmail(email)
        val isPasswordValid = registerValidator.validatePassword(password)
        val isConfirmPasswordValid = registerValidator.validateConfirmPassword(
            password = password,
            confirmPassword = confirmPassword
        )

        val emailError = if (!isEmailValid) {
            R.string.email_error
        } else null
        val passwordError = if (!isPasswordValid) {
            R.string.password_error
        } else null
        val confirmPasswordError = if (!isConfirmPasswordValid) {
            R.string.confirm_password_error
        } else null

        _state.update {
            it.copy(
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
        }

        return isEmailValid && isPasswordValid && isConfirmPasswordValid
    }
}