package com.hrconnect.android.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.android.R
import com.hrconnect.android.common.Constants
import com.hrconnect.android.presentation.util.ObserveAsEvents
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import com.hrconnect.uikit.presentation.components.buttons.PrimaryButton
import com.hrconnect.uikit.presentation.components.checkbox.HrCheckbox
import com.hrconnect.uikit.presentation.components.inputs.Input
import com.hrconnect.uikit.presentation.components.inputs.PasswordInput
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    onLoginClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            RegisterEvent.Success -> onLoginClick()
            else -> Unit
        }
    }

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
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center,
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
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = TextStyle(
                            fontFamily = Manrope,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            lineHeight = 32.sp,
                            letterSpacing = (-0.6).sp,
                            color = HrTheme.colorScheme.primaryVariant
                        )
                    )
                    Text(
                        modifier = Modifier.width(254.23.dp),
                        text = "Modern Talent Engine for HR Excellence",
                        style = HrTheme.typography.bodySmall,
                        color = HrTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )
                }
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
                            state = state.firstNameState,
                            label = "First Name",
                            isError = !state.isFirstNameValid
                        )
                        Input(
                            modifier = Modifier.weight(1f),
                            state = state.lastNameState,
                            label = "Last Name",
                            isError = !state.isLastNameValid
                        )
                    }
                    Input(
                        state = state.emailState,
                        label = "Email",
                        supportingText = "Lowercase letters and digits only (name@domain.ru)",
                        isError = !state.isEmailValid
                    )
                    PasswordInput(
                        state = state.passwordState,
                        isPasswordVisible = state.isPasswordVisible,
                        onTogglePasswordVisibility = {
                            onEvent(RegisterEvent.OnTogglePasswordVisibility)
                        },
                        label = "Password",
                        placeholder = "••••••••",
                        supportingText = "Minimum 8 characters",
                        leadingIcon = ImageVector.vectorResource(R.drawable.ic_lock),
                        isError = !state.isPasswordValid
                    )
                    PasswordInput(
                        state = state.confirmPasswordState,
                        isPasswordVisible = state.isConfirmPasswordVisible,
                        onTogglePasswordVisibility = {
                            onEvent(RegisterEvent.OnToggleConfirmPasswordVisibility)
                        },
                        label = "Confirm Password",
                        placeholder = "••••••••",
                        supportingText = if (!state.isConfirmPasswordValid) {
                            "Passwords do not match"
                        } else null,
                        leadingIcon = ImageVector.vectorResource(R.drawable.ic_lock),
                        isError = !state.isConfirmPasswordValid
                    )
                    HrCheckbox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 27.62.dp),
                        checked = state.acceptedTerms,
                        onCheckedChange = {
                            onEvent(RegisterEvent.OnAcceptTerms(it))
                        },
                        label = buildAnnotatedString {
                            append("I agree to the ")
                            withLink(
                                link = LinkAnnotation.Url(
                                    url = Constants.TERMS_OF_USE_URL,
                                    styles = TextLinkStyles(
                                        style = SpanStyle(
                                            color = HrTheme.colorScheme.primaryVariant
                                        )
                                    )
                                )
                            ) {
                                append("terms of use")
                            }
                            append(" and privacy policy.")
                        }
                    )
                    PrimaryButton(
                        modifier = Modifier.padding(vertical = 16.dp),
                        label = "Register",
                        onClick = {
                            onEvent(RegisterEvent.OnRegisterClick)
                        },
                        enabled = state.firstNameState.text.isNotBlank() &&
                                state.lastNameState.text.isNotBlank() &&
                                state.emailState.text.isNotBlank() &&
                                state.passwordState.text.isNotBlank() &&
                                state.confirmPasswordState.text.isNotBlank() &&
                                state.acceptedTerms
                    )
                }
                Text(
                    text = buildAnnotatedString {
                        append("Already have an account? ")
                        withLink(
                            link = LinkAnnotation.Clickable(
                                tag = "login",
                                styles = TextLinkStyles(
                                    style = SpanStyle(
                                        color = HrTheme.colorScheme.primaryVariant
                                    )
                                )
                            ) {
                                onEvent(RegisterEvent.OnLoginClick)
                            }
                        ) {
                            append("Sign in")
                        }
                    },
                    style = HrTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    HrTheme {
        RegisterScreen(
            state = RegisterState(),
            onEvent = {}
        )
    }
}