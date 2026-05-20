package com.hrconnect.android.presentation.create_vacancy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrconnect.android.R
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope

@Composable
fun CreateVacancyTopBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    offset = DpOffset(0.dp, 1.dp),
                    radius = 2.dp,
                    alpha = 0.05f
                )
            )
            .background(HrTheme.colorScheme.container)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.01.dp,
                    end = 20.dp
                )
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = null,
                        indication = ripple(bounded = false),
                        onClick = onCloseClick
                    )
                    .padding(8.dp)
                    .size(14.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                contentDescription = null,
                tint = Color(0xFF64748B)
            )
            Text(
                text = "Create Vacancy",
                style = TextStyle(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = (-0.4).sp,
                    color = HrTheme.colorScheme.topBarTitle
                )
            )
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = null,
                        indication = ripple(bounded = false),
                        onClick = {}
                    )
                    .padding(8.dp)
                    .size(4.dp, 16.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_more_vert),
                contentDescription = null,
                tint = Color(0xFF64748B)
            )
        }
        HorizontalDivider(
            color = HrTheme.colorScheme.bottomBarBorder
        )
    }
}