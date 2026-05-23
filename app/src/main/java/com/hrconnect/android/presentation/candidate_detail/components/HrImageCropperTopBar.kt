package com.hrconnect.android.presentation.candidate_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
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
fun HrImageCropperTopBar(
    onBackClick: () -> Unit,
    onHelpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
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
                .weight(1f)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clickable(
                        interactionSource = null,
                        indication = ripple(bounded = false),
                        onClick = onBackClick
                    )
                    .padding(8.dp)
                    .size(16.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = HrTheme.colorScheme.primaryVariant
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Update Profile Photo",
                style = TextStyle(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    letterSpacing = (-0.45).sp
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        interactionSource = null,
                        indication = ripple(bounded = false),
                        onClick = onHelpClick
                    ),
                imageVector = ImageVector.vectorResource(R.drawable.ic_help),
                contentDescription = null,
                tint = HrTheme.colorScheme.bottomBarContent
            )
        }
        HorizontalDivider(
            color = HrTheme.colorScheme.bottomBarBorder
        )
    }
}