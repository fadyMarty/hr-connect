package com.hrconnect.android.presentation.register

import androidx.lifecycle.ViewModel
import com.hrconnect.android.domain.repository.AuthRepository
import com.hrconnect.android.domain.validator.RegisterValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

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
                TODO()
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