package com.hrconnect.android.presentation.hr_board.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hrconnect.android.R
import com.hrconnect.uikit.common.theme.HrTheme

@Composable
fun HrFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = if (selected) {
                    HrTheme.colorScheme.primary.copy(alpha = 0.05f)
                } else {
                    HrTheme.colorScheme.container
                }
            )
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) {
                    HrTheme.colorScheme.primary
                } else {
                    HrTheme.colorScheme.border
                },
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = 16.dp,
                vertical = 13.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = HrTheme.typography.bodySmall,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            color = if (selected) {
                HrTheme.colorScheme.primary
            } else {
                HrTheme.colorScheme.onBackground
            }
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .then(
                    if (selected) {
                        Modifier.background(HrTheme.colorScheme.primary)
                    } else {
                        Modifier.border(
                            width = 2.dp,
                            color = HrTheme.colorScheme.border,
                            shape = RoundedCornerShape(4.dp)
                        )
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Icon(
                    modifier = Modifier.size(10.44.dp, 7.96.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_check),
                    contentDescription = null,
                    tint = HrTheme.colorScheme.onPrimary
                )
            }
        }
    }
}