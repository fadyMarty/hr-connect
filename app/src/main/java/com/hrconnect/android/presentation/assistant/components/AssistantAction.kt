package com.hrconnect.android.presentation.assistant.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope

@Composable
fun AssistantAction(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = HrTheme.colorScheme.container,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = HrTheme.colorScheme.border,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = HrTheme.colorScheme.containerBorder,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(16.dp, 20.dp),
                imageVector = icon,
                contentDescription = null,
                tint = HrTheme.colorScheme.primary
            )
        }
        Column {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp
                )
            )
            Text(
                text = description,
                style = TextStyle(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.sp,
                    color = HrTheme.colorScheme.fieldLabel
                )
            )
        }
    }
}