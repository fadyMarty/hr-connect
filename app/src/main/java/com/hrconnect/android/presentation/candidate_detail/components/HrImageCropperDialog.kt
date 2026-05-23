package com.hrconnect.android.presentation.candidate_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.attafitamim.krop.core.crop.CropState
import com.attafitamim.krop.core.crop.CropperStyle
import com.attafitamim.krop.core.crop.LocalCropperStyle
import com.attafitamim.krop.core.crop.rotLeft
import com.attafitamim.krop.core.crop.rotRight
import com.attafitamim.krop.ui.CropperPreview
import com.hrconnect.android.R
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@Composable
fun HrImageCropperDialog(
    state: CropState,
    modifier: Modifier = Modifier,
    style: CropperStyle = hrCropperStyle(),
) {
    val hazeState = rememberHazeState()

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
                        modifier = Modifier
                            .fillMaxSize()
                            .hazeSource(hazeState),
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
                            },
                            hazeState = hazeState
                        )
                        ControlIconButton(
                            icon = ImageVector.vectorResource(R.drawable.ic_rotate_right),
                            onClick = {
                                state.rotRight()
                            },
                            hazeState = hazeState
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