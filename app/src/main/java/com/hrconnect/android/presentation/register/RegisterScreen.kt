package com.hrconnect.android.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.android.common.util.Constants
import com.hrconnect.android.common.util.ObserveAsEvents
import com.hrconnect.android.common.util.TestTags
import com.hrconnect.android.common.util.supportingTextColor
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.presentation.components.buttons.PrimaryButton
import com.hrconnect.uikit.presentation.components.checkbox.HrCheckbox
import com.hrconnect.uikit.presentation.components.inputs.Input
import com.hrconnect.uikit.presentation.components.inputs.PasswordInput
import kotlinx.coroutines.launch
import logcat.LogPriority.INFO
import logcat.logcat
import org.koin.compose.viewmodel.koinViewModel

/**
 * Экран регистрации.
 *
 * Дата создания: 26-05-2026.
 * Автор создания: 1.
 */
@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    onLoginClick: () -> Unit,
    onRegisterSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            RegisterEvent.OnRegisterSuccess -> {
                onRegisterSuccess()
            }
            is RegisterEvent.OnError -> {
                scope.launch {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    RegisterScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onAction = { action ->
            when (action) {
                RegisterAction.OnLoginClick -> onLoginClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val emailSupportingTextColor = if (state.isEmailValid) {
        HrTheme.colorScheme.fieldLabel
    } else {
        HrTheme.colorScheme.error
    }
    val passwordSupportingTextColor = if (state.isPasswordValid) {
        HrTheme.colorScheme.fieldLabel
    } else {
        HrTheme.colorScheme.error
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    horizontal = 20.dp,
                    vertical = 36.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .dropShadow(
                        shape = RoundedCornerShape(12.dp),
                        shadow = Shadow(
                            offset = DpOffset(0.dp, 4.dp),
                            radius = 12.dp,
                            alpha = 0.05f
                        )
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(HrTheme.colorScheme.container)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "HR Connect",
                    style = HrTheme.typography.screenHeader,
                    color = HrTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Портал управления талантами",
                    style = HrTheme.typography.fieldLabel,
                    color = HrTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Input(
                            modifier = Modifier.weight(1f),
                            inputModifier = Modifier.testTag(TestTags.FIRST_NAME_INPUT),
                            state = state.firstNameState,
                            label = "Имя",
                            placeholder = "Джон",
                            isError = !state.isFirstNameValid
                        )
                        Input(
                            modifier = Modifier.weight(1f),
                            inputModifier = Modifier.testTag(TestTags.LAST_NAME_INPUT),
                            state = state.lastNameState,
                            label = "Фамилия",
                            placeholder = "Доу",
                            isError = !state.isLastNameValid
                        )
                    }
                    Input(
                        inputModifier = Modifier
                            .testTag(TestTags.EMAIL_INPUT)
                            .semantics {
                                supportingTextColor = emailSupportingTextColor
                            },
                        label = "Email",
                        state = state.emailState,
                        placeholder = "name@domain.ru",
                        supportingText = "Только строчные буквы и цифры (name@domain.ru)",
                        isError = !state.isEmailValid
                    )
                    PasswordInput(
                        inputModifier = Modifier
                            .testTag(TestTags.PASSWORD_INPUT)
                            .semantics {
                                supportingTextColor = passwordSupportingTextColor
                            },
                        state = state.passwordState,
                        isPasswordVisible = state.isPasswordVisible,
                        onTogglePasswordVisibility = {
                            onAction(RegisterAction.OnTogglePasswordVisibility)
                            logcat(INFO) { "Изменена видимость пароля на ${state.isPasswordVisible}" }
                        },
                        label = "Пароль",
                        placeholder = "••••••••",
                        isError = !state.isPasswordValid
                    )
                    PasswordInput(
                        inputModifier = Modifier.testTag(TestTags.CONFIRM_PASSWORD_INPUT),
                        state = state.confirmPasswordState,
                        isPasswordVisible = state.isConfirmPasswordVisible,
                        onTogglePasswordVisibility = {
                            onAction(RegisterAction.OnToggleConfirmPasswordVisibility)
                            logcat(INFO) { "Изменена видимость подтверждения пароля на ${state.isConfirmPasswordVisible}" }
                        },
                        label = "Подтвердить пароль",
                        placeholder = "••••••••",
                        supportingText = state.confirmPasswordError?.let {
                            stringResource(it)
                        },
                        isError = state.confirmPasswordError != null
                    )
                    HrCheckbox(
                        modifier = Modifier.padding(end = 27.62.dp),
                        checkboxModifier = Modifier
                            .padding(top = 4.dp)
                            .testTag(TestTags.TERMS_CHECKBOX),
                        checked = state.acceptedTerms,
                        onCheckedChange = {
                            onAction(RegisterAction.OnAcceptTerms(it))
                        },
                        label = buildAnnotatedString {
                            append("Я согласен с ")
                            withLink(
                                link = LinkAnnotation.Url(
                                    url = Constants.TERMS_OF_USE_URL,
                                    styles = TextLinkStyles(
                                        style = SpanStyle(
                                            color = HrTheme.colorScheme.primary
                                        )
                                    )
                                )
                            ) {
                                append("условиями использования")
                            }
                            append(" и политикой конфиденциальности.")
                        }
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                PrimaryButton(
                    modifier = Modifier.testTag(TestTags.REGISTER_BUTTON),
                    label = "Зарегистрироваться",
                    onClick = {
                        onAction(RegisterAction.OnRegisterClick)
                        logcat(INFO) { "Нажата кнопка регистрации" }
                    },
                    enabled = state.acceptedTerms
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = buildAnnotatedString {
                        append("У тебя уже есть аккаунт? ")
                        withLink(
                            link = LinkAnnotation.Clickable(
                                tag = "LOGIN",
                                styles = TextLinkStyles(
                                    style = SpanStyle(
                                        color = HrTheme.colorScheme.primary
                                    )
                                )
                            ) {
                                onAction(RegisterAction.OnLoginClick)
                                logcat(INFO) { "Нажат текст входа в аккаунт" }
                            }
                        ) {
                            append("Войти")
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HrTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}