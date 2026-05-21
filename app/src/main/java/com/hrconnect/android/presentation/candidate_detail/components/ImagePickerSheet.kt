package com.hrconnect.android.presentation.candidate_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrconnect.android.R
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerSheet(
    onDismiss: () -> Unit,
    onCameraPickerClick: () -> Unit,
    onGalleryPickerClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp
        ),
        containerColor = HrTheme.colorScheme.container,
        scrimColor = HrTheme.colorScheme.onBackground.copy(alpha = 0.4f),
        dragHandle = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp, 4.dp)
                        .clip(CircleShape)
                        .background(HrTheme.colorScheme.border)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Update Profile Photo",
                style = HrTheme.typography.subheader,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Choose a new profile photo for the candidate profile.",
                style = HrTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = HrTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(32.dp))
            PhotoPickerItem(
                icon = ImageVector.vectorResource(R.drawable.ic_photo_camera),
                iconTint = Color(0xFF00174B),
                label = "Take Photo",
                onClick = onCameraPickerClick
            )
            Spacer(modifier = Modifier.height(8.dp))
            PhotoPickerItem(
                icon = ImageVector.vectorResource(R.drawable.ic_image),
                iconTint = HrTheme.colorScheme.onBackgroundVariant,
                label = "Choose from Gallery",
                onClick = onGalleryPickerClick
            )
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider(
                color = HrTheme.colorScheme.border
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(onClick = onDismiss)
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cancel",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp,
                        textAlign = TextAlign.Center,
                        color = HrTheme.colorScheme.error
                    )
                )
            }
        }
    }
}