package com.hrconnect.android.presentation.candidate_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.hrconnect.uikit.common.theme.HrTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

@Composable
fun ControlIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    hazeState: HazeState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .hazeEffect(
                state = hazeState,
                style = HazeStyle(
                    backgroundColor = HrTheme.colorScheme.container,
                    tint = HazeTint(
                        color = HrTheme.colorScheme.container.copy(0.2f)
                    ),
                    blurRadius = 12.dp
                )
            )
            .background(
                HrTheme.colorScheme.container.copy(0.1f)
            )
            .border(
                width = 1.dp,
                color = HrTheme.colorScheme.container.copy(alpha = 0.2f),
                shape = CircleShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(17.95.dp, 20.95.dp),
            imageVector = icon,
            contentDescription = null,
            tint = HrTheme.colorScheme.container
        )
    }
}