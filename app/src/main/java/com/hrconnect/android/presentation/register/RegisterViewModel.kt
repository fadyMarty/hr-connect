package com.hrconnect.android.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.android.domain.use_case.ValidateEmailUseCase
import com.hrconnect.android.domain.use_case.ValidatePasswordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
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
            RegisterEvent.OnRegisterClick -> {
                register()
            }
            else -> Unit
        }
    }

    private fun register() {
        if (!validateFormInputs()) {
            return
        }

        viewModelScope.launch {
            eventChannel.send(RegisterEvent.Success)
        }
    }

    private fun validateFormInputs(): Boolean {
        val firstName = state.value.firstNameState.text.toString()
        val lastName = state.value.lastNameState.text.toString()
        val email = state.value.emailState.text.toString()
        val password = state.value.passwordState.text.toString()
        val confirmPassword = state.value.confirmPasswordState.text.toString()

        val isFirstNameValid = firstName.isNotBlank()
        val isLastNameValid = lastName.isNotBlank()
        val isEmailValid = validateEmailUseCase(email)
        val isPasswordValid = validatePasswordUseCase(password)
        val isConfirmPasswordValid = password == confirmPassword

        _state.update {
            it.copy(
                isFirstNameValid = isFirstNameValid,
                isLastNameValid = isLastNameValid,
                isEmailValid = isEmailValid,
                isPasswordValid = isPasswordValid,
                isConfirmPasswordValid = isConfirmPasswordValid
            )
        }

        return isFirstNameValid && isLastNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid
    }
}