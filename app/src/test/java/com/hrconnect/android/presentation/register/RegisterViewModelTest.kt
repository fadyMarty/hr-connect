package com.hrconnect.android.presentation.register

import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import com.google.common.truth.Truth.assertThat
import com.hrconnect.android.data.validator.RegisterValidatorImpl
import com.hrconnect.android.domain.validator.RegisterValidator
import com.hrconnect.netlib.auth.domain.repository.AuthRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class RegisterViewModelTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var registerValidator: RegisterValidator
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        authRepository = mockk()
        registerValidator = RegisterValidatorImpl()
        viewModel = RegisterViewModel(
            authRepository = authRepository,
            registerValidator = registerValidator
        )
    }

    @Test
    fun `успешная регистрация отправляет OnSuccess`() = runTest {
        fillValidInputForm()
        coEvery { authRepository.register() } returns Result.success(Unit)
        viewModel.onEvent(RegisterEvent.OnRegisterClick)

        assertThat(viewModel.events.first()).isEqualTo(RegisterEvent.OnSuccess)
    }

    @Test
    fun `провальная регистрация отправляет OnError`() = runTest {
        val errorMessage = "Conflict — email уже зарегистрирован"

        fillValidInputForm()
        coEvery { authRepository.register() } returns Result.failure(
            HttpException(
                Response.error<Any>(
                    409,
                    "Conflict — email уже зарегистрирован".toResponseBody()
                )
            )
        )
        viewModel.onEvent(RegisterEvent.OnRegisterClick)

        assertThat(viewModel.events.first()).isEqualTo(RegisterEvent.OnError(errorMessage))
    }

    private fun fillValidInputForm() {
        viewModel.state.value.firstNameState.setTextAndPlaceCursorAtEnd("John")
        viewModel.state.value.lastNameState.setTextAndPlaceCursorAtEnd("Doe")
        viewModel.state.value.emailState.setTextAndPlaceCursorAtEnd("name@domain.ru")
        viewModel.state.value.passwordState.setTextAndPlaceCursorAtEnd("12345678")
        viewModel.state.value.confirmPasswordState.setTextAndPlaceCursorAtEnd("12345678")
        viewModel.onEvent(RegisterEvent.OnAcceptTerms(isAccepted = true))
    }
}