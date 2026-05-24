package com.hrconnect.android.presentation.create_vacancy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrconnect.android.R
import com.hrconnect.android.common.util.dashedBorder
import com.hrconnect.android.presentation.create_vacancy.CreateVacancyEvent
import com.hrconnect.android.presentation.create_vacancy.CreateVacancyState
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.size

@Composable
fun CreateVacancySecondStep(
    state: CreateVacancyState,
    onEvent: (CreateVacancyEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val filePickerLauncher = rememberFilePickerLauncher(
        type = FileKitType.File(
            extensions = listOf("docx", "pdf")
        ),
        mode = FileKitMode.Multiple(
            maxItems = 5 - state.supportDocuments.size
        )
    ) { files ->
        files?.let {
            onEvent(CreateVacancyEvent.OnFilesSelected(files))
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 32.dp,
            end = 20.dp,
            bottom = 71.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Support Documents",
                    style = HrTheme.typography.subheader
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            HrTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                        )
                        .border(
                            width = 1.dp,
                            color = HrTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_info),
                        contentDescription = null,
                        tint = HrTheme.colorScheme.onPrimaryContainer
                    )
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Accepted Formats",
                            style = HrTheme.typography.fieldLabel,
                            color = HrTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = stringResource(R.string.accepted_formats_desc),
                            style = HrTheme.typography.bodySmall,
                            color = HrTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    state.supportDocuments.forEach { file ->
                        FileListItem(
                            fileName = file.name,
                            fileSize = file.size().toString(),
                            onDeleteClick = {
                                onEvent(CreateVacancyEvent.OnDeleteFileClick(file))
                            }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                filePickerLauncher.launch()
                            }
                            .padding(1.dp)
                            .dashedBorder(
                                color = HrTheme.colorScheme.border,
                                shape = RoundedCornerShape(12.dp),
                                width = 2.dp,
                                dashLength = 6.dp,
                                gapLength = 4.dp
                            )
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp, 25.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_upload_file),
                            contentDescription = null,
                            tint = HrTheme.colorScheme.primary
                        )
                        Text(
                            text = "Upload New File",
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
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateVacancySecondStepPreview() {
    HrTheme {
        CreateVacancySecondStep(
            state = CreateVacancyState(),
            onEvent = {}
        )
    }
}