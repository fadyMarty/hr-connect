package com.hrconnect.android.presentation.register

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.hrconnect.android.R
import com.hrconnect.android.common.util.TestTags
import com.hrconnect.android.data.validator.RegisterValidatorImpl
import com.hrconnect.android.domain.repository.AuthRepository
import com.hrconnect.android.domain.validator.RegisterValidator
import com.hrconnect.uikit.common.theme.HrTheme
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var registerValidator: RegisterValidator
    private lateinit var viewModel: RegisterViewModel

    private lateinit var emailError: String
    private lateinit var passwordError: String
    private lateinit var confirmPasswordError: String

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        authRepository = mockk()
        registerValidator = RegisterValidatorImpl()
        viewModel = RegisterViewModel(
            authRepository = authRepository,
            registerValidator = registerValidator
        )

        composeRule.setContent {
            HrTheme {
                emailError = stringResource(R.string.email_error)
                passwordError = stringResource(R.string.password_error)
                confirmPasswordError = stringResource(R.string.confirm_password_error)

                RegisterRoot(
                    viewModel = viewModel,
                    onLoginClick = {}
                )
            }
        }
    }

    @Test
    fun invalidEmail_showsError() {
        composeRule
            .onNodeWithTag(TestTags.FIRST_NAME_INPUT)
            .performTextInput("John")
        composeRule
            .onNodeWithTag(TestTags.LAST_NAME_INPUT)
            .performTextInput("Doe")
        composeRule
            .onNodeWithTag(TestTags.EMAIL_INPUT)
            .performTextInput("NAME@DOMAIN.ru")
        composeRule
            .onNodeWithTag(TestTags.PASSWORD_INPUT)
            .performTextInput("12345678")
        composeRule
            .onNodeWithTag(TestTags.CONFIRM_PASSWORD_INPUT)
            .performTextInput("12345678")
        composeRule
            .onNodeWithTag(TestTags.TERMS_CHECKBOX)
            .performClick()
        composeRule
            .onNodeWithTag(TestTags.REGISTER_BUTTON)
            .performClick()

        composeRule
            .onNodeWithText(emailError)
            .assertIsDisplayed()
    }

    @Test
    fun invalidPassword_showsError() {
        composeRule
            .onNodeWithTag(TestTags.FIRST_NAME_INPUT)
            .performTextInput("John")
        composeRule
            .onNodeWithTag(TestTags.LAST_NAME_INPUT)
            .performTextInput("Doe")
        composeRule
            .onNodeWithTag(TestTags.EMAIL_INPUT)
            .performTextInput("name@domain.ru")
        composeRule
            .onNodeWithTag(TestTags.PASSWORD_INPUT)
            .performTextInput("123")
        composeRule
            .onNodeWithTag(TestTags.CONFIRM_PASSWORD_INPUT)
            .performTextInput("123")
        composeRule
            .onNodeWithTag(TestTags.TERMS_CHECKBOX)
            .performClick()

        composeRule
            .onNodeWithTag(TestTags.REGISTER_BUTTON)
            .performClick()

        composeRule
            .onNodeWithText(passwordError)
            .assertIsDisplayed()
    }
}