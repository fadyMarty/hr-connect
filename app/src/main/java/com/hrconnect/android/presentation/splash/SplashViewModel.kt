package com.hrconnect.android.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.netlib.auth.domain.manager.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class SplashViewModel(
    private val tokenManager: TokenManager,
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    init {
        tokenManager.getToken().onEach { token ->
            _state.update {
                it.copy(
                    isLoggedIn = token != null,
                    isLoading = false
                )
            }
        }.launchIn(viewModelScope)
    }
}