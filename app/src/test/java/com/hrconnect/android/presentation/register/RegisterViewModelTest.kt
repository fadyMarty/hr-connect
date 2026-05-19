package com.hrconnect.android.presentation.register

import com.google.common.truth.Truth.assertThat
import com.hrconnect.android.domain.validator.RegisterValidator
import com.hrconnect.netlib.auth.domain.repository.AuthRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class RegisterViewModelTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var registerValidator: RegisterValidator
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        authRepository = mockk()
        registerValidator = mockk()
        viewModel = RegisterViewModel(
            authRepository = authRepository,
            registerValidator = registerValidator
        )
    }

    @Test
    fun `успешная регистрация отправляет OnSuccess`() = runTest {
        fillValidFormInputs()
        coEvery { authRepository.register() } returns Result.success(Unit)
        viewModel.onEvent(RegisterEvent.OnRegisterClick)

        assertThat(viewModel.events.first()).isEqualTo(RegisterEvent.OnSuccess)
    }

    @Test
    fun `провальная регистрация отправляет OnError`() = runTest {
        val errorMessage = "Conflict — email уже зарегистрирован"
        fillValidFormInputs()
        coEvery { authRepository.register() } returns Result.failure(
            mockk<HttpException> {
                every { code() } returns 409
                every { message() } returns errorMessage
            }
        )
        viewModel.onEvent(RegisterEvent.OnRegisterClick)

        assertThat(viewModel.events.first()).isEqualTo(RegisterEvent.OnError(errorMessage))
    }

    private fun fillValidFormInputs() {
        every { registerValidator.validateEmail(any()) } returns true
        every { registerValidator.validatePassword(any()) } returns true
        every { registerValidator.validateConfirmPassword(any(), any()) } returns true
    }
}