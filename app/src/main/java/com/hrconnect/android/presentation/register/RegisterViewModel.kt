package com.hrconnect.android.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.android.R
import com.hrconnect.android.domain.repository.AuthRepository
import com.hrconnect.android.domain.validator.RegisterValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * ViewModel экрана регистрации.
 *
 * Дата создания: 26-05-2026.
 * Автор создания: 1.
 */
class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val registerValidator: RegisterValidator,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnAcceptTerms -> {
                _state.update {
                    it.copy(
                        acceptedTerms = action.isAccepted
                    )
                }
            }
            RegisterAction.OnRegisterClick -> {
                val currentState = state.value
                val firstName = currentState.firstNameState.text.toString()
                val lastName = currentState.lastNameState.text.toString()
                val email = currentState.emailState.text.toString()
                val password = currentState.passwordState.text.toString()
                val confirmPassword = currentState.confirmPasswordState.text.toString()

                val isFirstNameValid = firstName.isNotBlank()
                val isLastNameValid = lastName.isNotBlank()
                val isEmailValid = registerValidator.validateEmail(email)
                val isPasswordValid = registerValidator.validatePassword(password)
                val isConfirmPasswordValid = registerValidator.validateConfirmPassword(
                    password = password,
                    confirmPassword = confirmPassword
                )

                val confirmPasswordError = if (!isConfirmPasswordValid) {
                    R.string.confirm_password_error
                } else null

                _state.update {
                    it.copy(
                        isFirstNameValid = isFirstNameValid,
                        isLastNameValid = isLastNameValid,
                        isEmailValid = isEmailValid,
                        isPasswordValid = isPasswordValid,
                        confirmPasswordError = confirmPasswordError
                    )
                }

                val allValid = isFirstNameValid &&
                        isLastNameValid &&
                        isEmailValid &&
                        isPasswordValid &&
                        isConfirmPasswordValid

                if (!allValid) {
                    return
                }

                viewModelScope.launch {
                    authRepository.register()
                        .onSuccess {
                            viewModelScope.launch {
                                eventChannel.send(RegisterEvent.OnRegisterSuccess)
                            }
                        }
                        .onFailure { e ->
                            viewModelScope.launch {
                                if (e is HttpException) {
                                    if (e.code() == 409) {
                                        eventChannel.send(
                                            RegisterEvent.OnError(
                                                message = "409 Conflict — email уже зарегистрирован"
                                            )
                                        )
                                    } else {
                                        eventChannel.send(RegisterEvent.OnError(e.message()))
                                    }
                                } else {
                                    eventChannel.send(
                                        RegisterEvent.OnError(e.message ?: "Что-то пошло не так")
                                    )
                                }
                            }
                        }
                }
            }
            RegisterAction.OnToggleConfirmPasswordVisibility -> {
                _state.update {
                    it.copy(
                        isConfirmPasswordVisible = !it.isConfirmPasswordVisible
                    )
                }
            }
            RegisterAction.OnTogglePasswordVisibility -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }
            else -> Unit
        }
    }
}