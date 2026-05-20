package com.hrconnect.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hrconnect.uikit.common.theme.HrTheme

@Composable
fun HrSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .width(44.dp)
            .clip(CircleShape)
            .background(HrTheme.colorScheme.primary)
            .clickable {
                onCheckedChange(!checked)
            }
            .padding(2.dp)
    ) {
        Spacer(
            modifier = Modifier.then(
                if (checked) {
                    Modifier.weight(1f)
                } else Modifier
            )
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(HrTheme.colorScheme.onPrimary)
                .border(
                    width = 1.dp,
                    color = HrTheme.colorScheme.onPrimary,
                    shape = CircleShape
                )
        )
    }
}