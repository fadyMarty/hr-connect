package com.hrconnect.android.presentation.candidate_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.attafitamim.krop.core.crop.AspectRatio
import com.attafitamim.krop.core.crop.CircleCropShape
import com.attafitamim.krop.core.crop.CropState
import com.attafitamim.krop.core.crop.CropperStyle
import com.attafitamim.krop.core.crop.LocalCropperStyle
import com.attafitamim.krop.core.crop.cropperStyle
import com.attafitamim.krop.core.crop.rotLeft
import com.attafitamim.krop.core.crop.rotRight
import com.attafitamim.krop.ui.CropperPreview
import com.hrconnect.android.R
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope

@Composable
fun HrImageCropperDialog(
    state: CropState,
    modifier: Modifier = Modifier,
    style: CropperStyle = cropperStyle(
        rectColor = Color.Transparent,
        touchRad = 55.dp,
        guidelines = null,
        overlay = Color.Black.copy(alpha = 0.6f),
        shapes = listOf(CircleCropShape),
        aspects = listOf(AspectRatio(1, 1))
    ),
) {
    LaunchedEffect(Unit) {
        state.setInitialState(style)
    }

    CompositionLocalProvider(LocalCropperStyle provides style) {
        Dialog(
            onDismissRequest = {
                state.done(accept = false)
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                HrImageCropperTopBar(
                    onBackClick = {
                        state.done(accept = false)
                    },
                    onHelpClick = {}
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clipToBounds()
                ) {
                    CropperPreview(
                        modifier = Modifier.fillMaxSize(),
                        state = state
                    )
                    TooltipInstructions(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 24.dp)
                            .height(176.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ControlIconButton(
                            icon = ImageVector.vectorResource(R.drawable.ic_rotate_left),
                            onClick = {
                                state.rotLeft()
                            }
                        )
                        ControlIconButton(
                            icon = ImageVector.vectorResource(R.drawable.ic_rotate_right),
                            onClick = {
                                state.rotRight()
                            }
                        )
                    }
                }
                HrImageCropperBottomBar(
                    onRetakeClick = {
                        state.done(accept = false)
                    },
                    onApplyClick = {
                        state.done(accept = true)
                    }
                )
            }
        }
    }
}

@Composable
private fun HrImageCropperTopBar(
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

@Composable
private fun HrImageCropperBottomBar(
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

@Composable
private fun TooltipInstructions(
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

@Composable
private fun ControlIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
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