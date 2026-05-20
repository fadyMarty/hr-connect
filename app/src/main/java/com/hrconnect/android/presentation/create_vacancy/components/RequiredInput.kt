package com.hrconnect.android.presentation.create_vacancy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.presentation.components.inputs.Input

@Composable
fun RequiredInput(
    state: TextFieldState,
    label: String,
    modifier: Modifier = Modifier,
    inputModifier: Modifier = Modifier,
    supportingText: String? = null,
    placeholder: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true,
    focused: Boolean = false,
    singleLine: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "$label ",
                style = HrTheme.typography.fieldLabel
            )
            Text(
                text = "*",
                style = HrTheme.typography.fieldLabel,
                color = HrTheme.colorScheme.error
            )
        }
        Input(
            state = state,
            modifier = modifier,
            inputModifier = inputModifier,
            supportingText = supportingText,
            placeholder = placeholder,
            isError = isError,
            enabled = enabled,
            focused = focused,
            singleLine = singleLine,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}