package com.hrconnect.android.presentation.vacancy_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrconnect.android.R
import com.hrconnect.android.presentation.vacancy_detail.components.AttachmentItem
import com.hrconnect.android.presentation.vacancy_detail.components.BentoGridInfoItem
import com.hrconnect.android.presentation.vacancy_detail.components.VacancyDetailTopBar
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun VacancyDetailRoot(
    viewModel: VacancyDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    VacancyDetailScreen(
        onEvent = { event ->
            when (event) {
                VacancyDetailEvent.OnBackClick -> onBackClick()
            }
        }
    )
}

@Composable
fun VacancyDetailScreen(
    onEvent: (VacancyDetailEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        VacancyDetailTopBar(
            onBackClick = {
                onEvent(VacancyDetailEvent.OnBackClick)
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 24.dp,
                end = 20.dp,
                bottom = 72.dp
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                HeaderSection()
            }
            item {
                BentoGridInfoSection()
            }
            item {
                MapSnippetSection()
            }
            item {
                DescriptionSection()
            }
            item {
                HrContactsSection()
            }
            item {
                AttachmentsSection()
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(HrTheme.colorScheme.primary)
                            .clickable {}
                            .padding(vertical = 18.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 7.99.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
                            contentDescription = null,
                            tint = HrTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "Edit Vacancy",
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = HrTheme.colorScheme.error,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {}
                            .padding(vertical = 17.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 7.99.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp, 18.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                            contentDescription = null,
                            tint = HrTheme.colorScheme.error
                        )
                        Text(
                            text = "Delete Vacancy",
                            style = TextStyle(
                                fontFamily = Manrope,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
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
    }
}

@Composable
private fun HeaderSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFDCFCE7),
                        shape = CircleShape
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 4.dp
                    )
            ) {
                Text(
                    text = "ACTIVE",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.6.sp,
                        color = Color(0xFF15803D)
                    )
                )
            }
            Text(
                text = "ID: VAC-2024-082",
                style = HrTheme.typography.bodySmall,
                color = HrTheme.colorScheme.fieldLabel
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Senior Product Designer",
            style = HrTheme.typography.screenHeader
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(13.5.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_apartment),
                    contentDescription = null,
                    tint = HrTheme.colorScheme.secondary
                )
                Text(
                    text = "Product Team",
                    style = HrTheme.typography.bodySmall,
                    color = HrTheme.colorScheme.secondary
                )
            }
            Text(
                text = "•",
                style = HrTheme.typography.bodyMedium,
                color = HrTheme.colorScheme.border
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(15.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_schedule),
                    contentDescription = null,
                    tint = HrTheme.colorScheme.secondary
                )
                Text(
                    text = "Full-time",
                    style = HrTheme.typography.bodySmall,
                    color = HrTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun BentoGridInfoSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BentoGridInfoItem(
                modifier = Modifier.weight(1f),
                title = "Salary",
                description = "$120k - $160k"
            )
            BentoGridInfoItem(
                modifier = Modifier.weight(1f),
                title = "Location",
                description = "Remote"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BentoGridInfoItem(
                modifier = Modifier.weight(1f),
                title = "Experience",
                description = "5+ years"
            )
            BentoGridInfoItem(
                modifier = Modifier.weight(1f),
                title = "Openings",
                description = "1 position"
            )
        }
    }
}

@Composable
private fun MapSnippetSection(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
            .dropShadow(
                shape = RoundedCornerShape(12.dp),
                shadow = Shadow(
                    offset = DpOffset(0.dp, 1.dp),
                    radius = 2.dp,
                    alpha = 0.05f
                )
            )
            .clip(RoundedCornerShape(12.dp))
            .background(HrTheme.colorScheme.container.copy(alpha = 0.002f))
            .border(
                width = 1.dp,
                color = HrTheme.colorScheme.checkboxContainerDisabled,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_san_francisco_map),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    bottom = 8.dp
                )
                .align(Alignment.BottomStart)
                .clip(RoundedCornerShape(4.dp))
                .background(HrTheme.colorScheme.container.copy(alpha = 0.8f))
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
        ) {
            Text(
                text = "SF HEADQUARTERS",
                style = TextStyle(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 1.sp,
                )
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(HrTheme.colorScheme.primary)
                .padding(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(16.dp, 20.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_location_on),
                contentDescription = null,
                tint = HrTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
private fun DescriptionSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            VerticalDivider(
                modifier = Modifier.height(24.dp),
                thickness = 4.dp,
                color = HrTheme.colorScheme.primary
            )
            Text(
                text = "Role Overview",
                style = HrTheme.typography.subheader
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontFamily = Manrope,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.sp,
                color = HrTheme.colorScheme.fieldLabel
            ),
            text = """
                            As a Senior Product Designer at our high-
                            growth startup, you will be instrumental in
                            defining the user experience for our flagship
                            recruitment platform. You will lead design
                            initiatives from initial concept through to final
                            hand-off, collaborating closely with product
                            managers and engineers.
                        """.trimIndent()
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontFamily = Manrope,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.sp,
                color = HrTheme.colorScheme.fieldLabel
            ),
            text = """
                            We are looking for someone who can balance
                            high-level strategic thinking with pixel-perfect
                            execution. You'll be responsible for building out
                            our design system, conducting user research,
                            and mentoring junior designers.
                        """.trimIndent()
        )
    }
}

@Composable
private fun HrContactsSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "HR Manager",
            style = HrTheme.typography.subheader
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(HrTheme.colorScheme.inputContainerDisabled)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(44.dp)
                    .dropShadow(
                        shape = CircleShape,
                        shadow = Shadow(
                            offset = DpOffset(0.dp, 1.dp),
                            radius = 2.dp,
                            alpha = 0.05f
                        )
                    )
                    .clip(CircleShape)
                    .background(HrTheme.colorScheme.container.copy(alpha = 0.002f))
                    .border(
                        width = 2.dp,
                        color = HrTheme.colorScheme.container,
                        shape = CircleShape
                    ),
                painter = painterResource(R.drawable.img_sarah_jenkins),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sarah Jenkins",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp
                    )
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Senior Talent\nAcquisition",
                    style = HrTheme.typography.bodySmall,
                    color = HrTheme.colorScheme.secondary
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ContactIconButton(
                    icon = ImageVector.vectorResource(R.drawable.ic_mail),
                    onClick = {}
                )
                ContactIconButton(
                    icon = ImageVector.vectorResource(R.drawable.ic_chat),
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun ContactIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    offset = DpOffset(0.dp, 1.dp),
                    radius = 2.dp,
                    alpha = 0.05f
                )
            )
            .clip(CircleShape)
            .background(HrTheme.colorScheme.container)
            .border(
                width = 1.dp,
                color = HrTheme.colorScheme.checkboxContainerDisabled,
                shape = CircleShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(16.67.dp),
            imageVector = icon,
            contentDescription = null,
            tint = HrTheme.colorScheme.primary
        )
    }
}

@Composable
fun AttachmentsSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AttachmentItem(
                fileName = "Job_Description.pdf",
                icon = ImageVector.vectorResource(R.drawable.ic_picture_as_pdf),
                iconTint = Color(0xFFEF4444),
                onDownloadClick = {}
            )
            AttachmentItem(
                fileName = "Benefits_Package.pdf",
                icon = ImageVector.vectorResource(R.drawable.ic_document),
                iconTint = Color(0xFF3B82F6),
                onDownloadClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VacancyDetailScreenPreview() {
    HrTheme {
        VacancyDetailScreen(
            onEvent = {}
        )
    }
}
