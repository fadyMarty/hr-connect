package com.hrconnect.android.presentation.candidate_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.RectangleShape
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
fun HrImageCropperBottomBar(
    onRetakeClick: () -> Unit,
    onApplyClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    offset = DpOffset(0.dp, (-4).dp),
                    radius = 12.dp,
                    alpha = 0.05f
                )
            )
            .background(HrTheme.colorScheme.container)
    ) {
        HorizontalDivider(
            color = HrTheme.colorScheme.bottomBarBorder
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .size(110.dp, 56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 1.dp,
                        color = HrTheme.colorScheme.primaryVariant,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(onClick = onRetakeClick),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 7.99.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_refresh),
                    contentDescription = null,
                    tint = HrTheme.colorScheme.primary

                )
                Text(
                    text = "Retake",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center,
                        color = HrTheme.colorScheme.primary
                    )
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .dropShadow(
                        shape = RoundedCornerShape(12.dp),
                        shadow = Shadow(
                            offset = DpOffset(0.dp, 4.dp),
                            radius = 6.dp,
                            spread = (-4).dp,
                            color = HrTheme.colorScheme.primary,
                            alpha = 0.2f
                        )
                    )
                    .dropShadow(
                        shape = RoundedCornerShape(12.dp),
                        shadow = Shadow(
                            offset = DpOffset(0.dp, 10.dp),
                            radius = 15.dp,
                            spread = (-3).dp,
                            color = HrTheme.colorScheme.primary,
                            alpha = 0.2f
                        )
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(HrTheme.colorScheme.primary)
                    .clickable(onClick = onApplyClick),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(16.3.dp, 12.02.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_check),
                    contentDescription = null,
                    tint = HrTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Apply",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center,
                        color = HrTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}