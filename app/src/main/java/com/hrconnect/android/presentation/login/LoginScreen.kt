package com.hrconnect.android.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.android.R
import com.hrconnect.android.common.util.Constants
import com.hrconnect.android.common.util.ObserveAsEvents
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import com.hrconnect.uikit.presentation.components.buttons.PrimaryButton
import com.hrconnect.uikit.presentation.components.checkbox.HrCheckbox
import com.hrconnect.uikit.presentation.components.inputs.Input
import com.hrconnect.uikit.presentation.components.inputs.PasswordInput
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            LoginEvent.OnSuccess -> onLoginSuccess()
            else -> Unit
        }
    }

    LoginScreen(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
) {
    val uriHandler = LocalUriHandler.current

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.img_login_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .dropShadow(
                            shape = RoundedCornerShape(12.dp),
                            shadow = Shadow(
                                offset = DpOffset(0.dp, 1.dp),
                                radius = 2.dp,
                                alpha = 0.05f
                            )
                        )
                        .background(
                            color = HrTheme.colorScheme.container,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(28.5.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_bubble_chart),
                        contentDescription = null,
                        tint = HrTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.app_name),
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        lineHeight = 32.sp,
                        letterSpacing = (-0.6).sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Talent Management Portal",
                    style = HrTheme.typography.bodySmall,
                    color = HrTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(32.dp))
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
                        .background(
                            color = HrTheme.colorScheme.container,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = HrTheme.colorScheme.containerBorder,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 24.dp)
                        .padding(top = 24.dp, bottom = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Input(
                        state = state.emailState,
                        label = "Login",
                        placeholder = "username or email",
                        isError = !state.isEmailValid
                    )
                    PasswordInput(
                        state = state.passwordState,
                        isPasswordVisible = state.isPasswordVisible,
                        onTogglePasswordVisibility = {
                            onEvent(LoginEvent.OnTogglePasswordVisibility)
                        },
                        label = "Password",
                        placeholder = "••••••••",
                        isError = !state.isPasswordValid
                    )
                    HrCheckbox(
                        checked = state.rememberUser,
                        onCheckedChange = {
                            onEvent(LoginEvent.OnRememberUserChecked(it))
                        },
                        label = AnnotatedString("Remember Me")
                    )
                    PrimaryButton(
                        label = "Login",
                        onClick = {
                            onEvent(LoginEvent.OnLoginClick)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) {
                            uriHandler.openUri(Constants.FORGOT_PASSWORD_URL)
                        },
                    text = "Forgot Password?",
                    style = HrTheme.typography.fieldLabel,
                    color = HrTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    HrTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = {}
        )
    }
}