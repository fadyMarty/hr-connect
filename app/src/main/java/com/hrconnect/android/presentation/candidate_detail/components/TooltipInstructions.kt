package com.hrconnect.android.presentation.candidate_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hrconnect.uikit.common.theme.HrTheme

@Composable
fun TooltipInstructions(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(bottom = 32.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                HrTheme.colorScheme.container.copy(alpha = 0.9f)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            text = "Pinch to zoom and drag to reposition",
            style = HrTheme.typography.bodyMedium,
            color = HrTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
    }
}