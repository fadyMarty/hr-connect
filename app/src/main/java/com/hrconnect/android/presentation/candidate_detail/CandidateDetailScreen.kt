package com.hrconnect.android.presentation.candidate_detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.attafitamim.krop.core.crop.CropResult
import com.attafitamim.krop.core.crop.ImageCropper
import com.attafitamim.krop.core.crop.crop
import com.attafitamim.krop.core.crop.rememberImageCropper
import com.attafitamim.krop.filekit.ImageFormat
import com.attafitamim.krop.filekit.encodeToByteArray
import com.attafitamim.krop.filekit.toImageSrc
import com.hrconnect.android.R
import com.hrconnect.android.presentation.candidate_detail.components.HrImageCropperDialog
import com.hrconnect.android.presentation.candidate_detail.components.ImagePickerSheet
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberCameraPickerLauncher
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CandidateDetailRoot(
    viewModel: CandidateDetailViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CandidateDetailScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun CandidateDetailScreen(
    state: CandidateDetailState,
    onEvent: (CandidateDetailEvent) -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val imageCropper = rememberImageCropper()
    val cropState = imageCropper.cropState
    val cameraPickerLauncher = rememberCameraPickerLauncher { file ->
        file?.let {
            scope.launch {
                imageCropper.cropPhotoFile(file) { bytes ->
                    onEvent(CandidateDetailEvent.OnPhotoSelected(bytes))
                }
            }
        }
    }
    val galleryPickerLauncher = rememberFilePickerLauncher(
        type = FileKitType.Image
    ) { file ->
        file?.let {
            scope.launch {
                imageCropper.cropPhotoFile(file) { bytes ->
                    onEvent(CandidateDetailEvent.OnPhotoSelected(bytes))
                }
            }
        }
    }
    val contentBlur by animateDpAsState(
        targetValue = if (state.isPhotoPickerSheetOpen) 2.dp else 0.dp
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .blur(contentBlur)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 16.dp,
                end = 20.dp,
                bottom = 74.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .dropShadow(
                            shape = RoundedCornerShape(12.dp),
                            shadow = Shadow(
                                offset = DpOffset(0.dp, 4.dp),
                                radius = 12.dp,
                                alpha = 0.05f
                            )
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(HrTheme.colorScheme.container)
                        .border(
                            width = 1.dp,
                            color = HrTheme.colorScheme.bottomBarBorder,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box {
                        AsyncImage(
                            modifier = Modifier
                                .size(128.dp)
                                .dropShadow(
                                    shape = CircleShape,
                                    shadow = Shadow(
                                        offset = DpOffset(0.dp, 4.dp),
                                        radius = 6.dp,
                                        spread = (-4).dp,
                                        alpha = 0.1f
                                    )
                                )
                                .dropShadow(
                                    shape = CircleShape,
                                    shadow = Shadow(
                                        offset = DpOffset(0.dp, 10.dp),
                                        radius = 15.dp,
                                        spread = (-3).dp,
                                        alpha = 0.1f
                                    )
                                )
                                .clip(CircleShape)
                                .background(HrTheme.colorScheme.container.copy(alpha = 0.2f))
                                .border(
                                    width = 4.dp,
                                    color = HrTheme.colorScheme.primary,
                                    shape = CircleShape
                                ),
                            model = ImageRequest.Builder(context)
                                .data(state.photoBytes)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        Icon(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .clip(CircleShape)
                                .background(HrTheme.colorScheme.primary)
                                .padding(8.dp)
                                .size(16.5.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_verified),
                            contentDescription = null,
                            tint = HrTheme.colorScheme.onPrimary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                width = 1.dp,
                                color = HrTheme.colorScheme.primary,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                onEvent(CandidateDetailEvent.OnChangePhotoClick)
                            }
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Change Photo",
                            style = TextStyle(
                                fontFamily = Manrope,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                                textAlign = TextAlign.Center
                            ),
                            color = HrTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }

    if (state.isPhotoPickerSheetOpen) {
        ImagePickerSheet(
            onDismiss = {
                onEvent(CandidateDetailEvent.OnDismissPhotoPickerSheet)
            },
            onCameraPickerClick = {
                onEvent(CandidateDetailEvent.OnDismissPhotoPickerSheet)
                cameraPickerLauncher.launch()
            },
            onGalleryPickerClick = {
                onEvent(CandidateDetailEvent.OnDismissPhotoPickerSheet)
                galleryPickerLauncher.launch()
            }
        )
    }

    if (cropState != null) {
        HrImageCropperDialog(
            state = cropState
        )
    }
}

private suspend fun ImageCropper.cropPhotoFile(
    file: PlatformFile,
    onSuccess: (ByteArray) -> Unit,
) {
    when (val result = crop(file.toImageSrc())) {
        is CropResult.Success -> {
            val bytes = result.bitmap.encodeToByteArray(
                format = ImageFormat.JPEG,
                quality = 100
            )
            onSuccess(bytes)
        }
        else -> Unit
    }
}

@Preview(showBackground = true)
@Composable
private fun CandidateDetailScreenPreview() {
    HrTheme {
        CandidateDetailScreen(
            state = CandidateDetailState(),
            onEvent = {}
        )
    }
}