package com.hrconnect.android.presentation.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoadingViewModel : ViewModel() {

    private val _progress = MutableStateFlow(0f)
    val progress = _progress.asStateFlow()

    private val eventChannel = Channel<LoadingEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val tickDuration = TOTAL_DURATION / STEPS
            repeat(STEPS) { step ->
                _progress.value = (step + 1f) / STEPS
                delay(tickDuration)
            }
            eventChannel.send(LoadingEvent.Success)
        }
    }

    companion object {
        private const val TOTAL_DURATION = 2000L
        private const val STEPS = 3
    }
}