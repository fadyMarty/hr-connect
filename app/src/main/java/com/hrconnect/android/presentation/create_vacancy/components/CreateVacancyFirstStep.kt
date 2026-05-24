package com.hrconnect.android.presentation.create_vacancy.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
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
import com.hrconnect.android.R
import com.hrconnect.android.common.util.dashedBorder
import com.hrconnect.android.presentation.components.HrSwitch
import com.hrconnect.android.presentation.create_vacancy.CreateVacancyEvent
import com.hrconnect.android.presentation.create_vacancy.CreateVacancyState
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import com.hrconnect.uikit.presentation.components.inputs.Input
import com.hrconnect.uikit.presentation.components.select.Select

@Composable
fun CreateVacancyFirstStep(
    state: CreateVacancyState,
    onEvent: (CreateVacancyEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 24.dp,
            end = 20.dp,
            bottom = 92.dp
        ),
        verticalArrangement = Arrangement.spacedBy(26.5.dp)
    ) {
        item {
            FormSection(
                state = state,
                onEvent = onEvent
            )
        }
        item {
            SurfaceCard()
        }
    }
}

@Composable
private fun FormSection(
    state: CreateVacancyState,
    onEvent: (CreateVacancyEvent) -> Unit,
) {
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
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        RequiredInput(
            state = state.vacancyTitleState,
            label = "Vacancy Title",
            placeholder = "e.g. Senior UX Designer"
        )
        Select(
            items = listOf(
                "Product & Design",
                "Engineering",
                "Operations"
            ),
            selectedItem = state.department,
            onItemClick = { department ->
                onEvent(
                    CreateVacancyEvent.OnDepartmentSelected(department)
                )
            },
            label = "Department"
        )
        RequiredInput(
            state = state.openingDateState,
            label = "Opening Date",
            placeholder = "24.05.2024",
            trailingIcon = ImageVector.vectorResource(
                R.drawable.ic_calendar_today
            )
        )
        RequiredSelect(
            items = listOf("Full-time"),
            selectedItem = state.position,
            onItemClick = { position ->
                onEvent(
                    CreateVacancyEvent.OnPositionSelected(position)
                )
            },
            label = "Position"
        )
        RequiredInput(
            state = state.salaryRangeState,
            label = "Salary Range",
            placeholder = "5,000 - 8,000",
            leadingIcon = ImageVector.vectorResource(R.drawable.ic_payments)
        )
        RequiredInput(
            state = state.requiredQuantityState,
            label = "Required Quantity"
        )
        Input(
            state = state.experienceState,
            label = "Experience",
            placeholder = "e.g. 5+ years"
        )
        Input(
            state = state.cityState,
            label = "City",
            trailingIcon = ImageVector.vectorResource(
                R.drawable.ic_location_on_outline
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Public Vacancy",
                    style = HrTheme.typography.fieldLabel
                )
                Text(
                    text = "Show this vacancy on the company career page",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        letterSpacing = 0.sp,
                        color = HrTheme.colorScheme.description
                    )
                )
            }
            HrSwitch(
                checked = state.publicVacancy,
                onCheckedChange = { publicVacancy ->
                    onEvent(
                        CreateVacancyEvent.OnPublicVacancyChecked(publicVacancy)
                    )
                }
            )
        }
    }
}

@Composable
private fun SurfaceCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.4f)
            .height(128.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(HrTheme.colorScheme.indicatorTrack)
            .padding(1.dp)
            .dashedBorder(
                color = HrTheme.colorScheme.border,
                shape = RoundedCornerShape(12.dp),
                strokeWidth = 2.dp,
                dashLength = 6.dp,
                gapLength = 4.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 47.62.dp,
                    end = 47.61.dp
                ),
            text = "Step 2: Candidate Qualifications & Skills will appear next",
            textAlign = TextAlign.Center,
            style = HrTheme.typography.bodyMedium,
            color = HrTheme.colorScheme.description
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateVacancyFirstStepPreview() {
    HrTheme {
        CreateVacancyFirstStep(
            state = CreateVacancyState(),
            onEvent = {}
        )
    }
}