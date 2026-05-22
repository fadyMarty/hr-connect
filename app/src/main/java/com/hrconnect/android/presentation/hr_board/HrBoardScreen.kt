package com.hrconnect.android.presentation.hr_board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.android.R
import com.hrconnect.android.presentation.hr_board.components.ActiveFilterChip
import com.hrconnect.android.presentation.hr_board.components.HrFilterChip
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import com.hrconnect.uikit.presentation.components.inputs.Input
import com.hrconnect.uikit.presentation.components.select.Select
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HrBoardRoot(
    viewModel: HrBoardViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HrBoardScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalGridApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HrBoardScreen(
    state: HrBoardState,
    onEvent: (HrBoardEvent) -> Unit,
) {
    val statuses = remember {
        listOf(
            "New Applicants",
            "Interviewing",
            "Offer Sent",
            "Hired"
        )
    }
    val departments = remember {
        listOf(
            "Design",
            "Engineering",
            "QA Department",
            "Operations"
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
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
                        horizontal = 20.dp,
                        vertical = 12.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = null,
                            indication = ripple(bounded = false),
                            onClick = {}
                        )
                        .padding(4.dp)
                        .size(18.dp, 12.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_menu),
                    contentDescription = null,
                    tint = Color(0xFF64748B)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "HR Board",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        letterSpacing = (-0.5).sp,
                        color = HrTheme.colorScheme.topBarTitle
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = null,
                            indication = ripple(bounded = false),
                            onClick = {
                                onEvent(HrBoardEvent.OnFilterMenuClick)
                            }
                        )
                        .padding(4.dp)
                        .size(18.dp, 12.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_filter_list),
                    contentDescription = null,
                    tint = HrTheme.colorScheme.primaryVariant
                )
            }
            HorizontalDivider(
                color = HrTheme.colorScheme.bottomBarBorder
            )
        }
    }

    if (state.showFilterDialog) {
        Dialog(
            onDismissRequest = {
                onEvent(HrBoardEvent.OnDismissFilterDialog)
            },
            properties = DialogProperties(
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(HrTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
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
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = null,
                                    indication = ripple(bounded = false)
                                ) {
                                    onEvent(HrBoardEvent.OnDismissFilterDialog)
                                }
                                .padding(8.dp)
                                .size(14.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                            contentDescription = null,
                            tint = Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Filters",
                            style = TextStyle(
                                fontFamily = Manrope,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                lineHeight = 28.sp,
                                letterSpacing = (-0.45).sp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = null,
                                    indication = null
                                ) {
                                    onEvent(HrBoardEvent.OnResetClick)
                                },
                            text = "Reset",
                            style = TextStyle(
                                fontFamily = Manrope,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                                textAlign = TextAlign.Center,
                                color = HrTheme.colorScheme.primaryVariant
                            )
                        )
                    }
                    HorizontalDivider(
                        color = HrTheme.colorScheme.bottomBarBorder
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 16.dp,
                        end = 20.dp,
                        bottom = 19.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Active Filters",
                                style = HrTheme.typography.subheader,
                                color = HrTheme.colorScheme.fieldLabel
                            )
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(14.67.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (state.department != null) {
                                    ActiveFilterChip(
                                        label = state.department,
                                        onClick = {
                                            onEvent(HrBoardEvent.OnDepartmentSelected(null))
                                        }
                                    )
                                }
                                state.statuses.forEach { status ->
                                    ActiveFilterChip(
                                        label = status,
                                        onClick = {
                                            onEvent(HrBoardEvent.OnStatusSelected(status))
                                        }
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Input(
                            state = state.vacancyState,
                            label = "Vacancy",
                            placeholder = "Search by job title...",
                            leadingIcon = ImageVector.vectorResource(R.drawable.ic_search)
                        )
                    }
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.5.dp)
                        ) {
                            Text(
                                text = "Candidate Status",
                                style = HrTheme.typography.fieldLabel,
                                color = HrTheme.colorScheme.fieldLabel
                            )
                            Grid(
                                config = {
                                    repeat(2) {
                                        column(1.fr)
                                    }
                                    gap(8.dp)
                                }
                            ) {
                                statuses.forEach { status ->
                                    HrFilterChip(
                                        label = status,
                                        selected = state.statuses.contains(status),
                                        onClick = {
                                            onEvent(HrBoardEvent.OnStatusSelected(status))
                                        }
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Select(
                            items = departments,
                            onItemClick = { department ->
                                onEvent(HrBoardEvent.OnDepartmentSelected(department))
                            },
                            selectedItem = state.department,
                            label = "Department"
                        )
                    }
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Input(
                                state = state.cityState,
                                placeholder = "London, New York, Remote...",
                                leadingIcon = ImageVector.vectorResource(R.drawable.ic_location_on_outline)
                            )
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                CityItem(
                                    label = "POPULAR: SAN FRANCISCO"
                                )
                                CityItem(
                                    label = "BERLIN"
                                )
                            }
                        }
                    }
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Salary Range (Yearly)",
                                    style = HrTheme.typography.fieldLabel,
                                    color = HrTheme.colorScheme.fieldLabel
                                )
                                Text(
                                    text = "$${formatSalary(state.salaryRange.start.toInt())} — " +
                                            "$${formatSalary(state.salaryRange.endInclusive.toInt())}",
                                    style = TextStyle(
                                        fontFamily = Manrope,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        letterSpacing = 0.sp
                                    ),
                                    color = HrTheme.colorScheme.primary
                                )
                            }
                            RangeSlider(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.salaryRange,
                                onValueChange = {
                                    onEvent(HrBoardEvent.OnSalaryRangeChanged(it))
                                },
                                valueRange = 0f..200000f,
                                startThumb = {
                                    SliderThumb()
                                },
                                endThumb = {
                                    SliderThumb()
                                },
                                track = { rangeSliderState ->
                                    SliderDefaults.Track(
                                        modifier = Modifier.height(6.dp),
                                        rangeSliderState = rangeSliderState,
                                        colors = SliderDefaults.colors(
                                            inactiveTrackColor = HrTheme.colorScheme.indicatorTrack
                                        ),
                                        drawStopIndicator = null,
                                        thumbTrackGapSize = (-10).dp,
                                        trackInsideCornerSize = 3.dp
                                    )
                                }
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            HrTheme.colorScheme.container.copy(alpha = 0.9f)
                        )
                ) {
                    HorizontalDivider(
                        color = HrTheme.colorScheme.bottomBarBorder
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp,
                                top = 20.dp,
                                end = 20.dp,
                                bottom = 32.dp
                            ),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    width = 1.dp,
                                    color = HrTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onEvent(HrBoardEvent.OnResetClick)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Reset all",
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
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    color = HrTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onEvent(HrBoardEvent.OnApplyFiltersClick)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Apply Filters",
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
        }
    }
}

private fun formatSalary(salary: Int): String {
    return when {
        salary >= 1000 -> "${salary / 1000}k"
        else -> salary.toString()
    }
}

@Composable
private fun CityItem(
    label: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(HrTheme.colorScheme.checkboxContainerDisabled)
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            ),
        text = label,
        style = TextStyle(
            fontFamily = Manrope,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.5.sp,
            color = HrTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
private fun SliderThumb(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(20.dp)
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
                width = 2.dp,
                color = HrTheme.colorScheme.primary,
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun HrBoardScreenPreview() {
    HrTheme {
        HrBoardScreen(
            state = HrBoardState(),
            onEvent = {}
        )
    }
}