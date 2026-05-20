package com.hrconnect.android.presentation.create_vacancy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrconnect.android.R
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope

@Composable
fun CreateVacancyBottomBar(
    onSaveDraftClick: () -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .dropShadow(
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp
                ),
                shadow = Shadow(
                    offset = DpOffset(0.dp, (-4).dp),
                    radius = 12.dp,
                    alpha = 0.05f
                )
            )
            .clip(
                RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp
                )
            )
            .background(
                HrTheme.colorScheme.container.copy(alpha = 0.8f)
            )
    ) {
        HorizontalDivider(
            color = HrTheme.colorScheme.bottomBarBorder
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    start = 48.11.dp,
                    end = 48.13.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(onClick = onSaveDraftClick)
                    .padding(
                        horizontal = 24.dp,
                        vertical = 4.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_save),
                    contentDescription = null,
                    tint = Color(0xFF64748B)
                )
                Text(
                    text = "Save Draft",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        lineHeight = 16.5.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF64748B)
                    )
                )
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFEFF6FF))
                    .clickable(onClick = onContinueClick)
                    .padding(
                        horizontal = 40.dp,
                        vertical = 8.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_forward),
                    contentDescription = null,
                    tint = Color(0xFF1D4ED8)
                )
                Text(
                    text = "Continue",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        lineHeight = 16.5.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF1D4ED8)
                    )
                )
            }
        }
    }
}