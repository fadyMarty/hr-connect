package com.hrconnect.android.presentation.create_vacancy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrconnect.android.R
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope

@Composable
fun FileListItem(
    fileName: String,
    fileSize: String,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(HrTheme.colorScheme.inputContainerDisabled)
            .border(
                width = 1.dp,
                color = HrTheme.colorScheme.border.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp, 20.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_file),
            contentDescription = null,
            tint = HrTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = fileName,
                style = HrTheme.typography.fieldLabel
            )
            Text(
                text = fileSize,
                style = TextStyle(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.sp,
                    color = HrTheme.colorScheme.description
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier
                .size(16.dp, 18.dp)
                .clickable(
                    interactionSource = null,
                    indication = ripple(bounded = false),
                    onClick = onDeleteClick
                ),
            imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
            contentDescription = null,
            tint = HrTheme.colorScheme.fieldLabel
        )
    }
}