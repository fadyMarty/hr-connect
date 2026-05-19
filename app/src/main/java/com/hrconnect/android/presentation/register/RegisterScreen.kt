package com.hrconnect.android.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.uikit.common.theme.HrTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    onLoginClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                RegisterEvent.OnLoginClick -> onLoginClick()
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
) {

}

@Preview(showSystemUi = true)
@Composable
private fun RegisterScreenPreview() {
    HrTheme {
        RegisterScreen(
            state = RegisterState(),
            onEvent = {}
        )
    }
}