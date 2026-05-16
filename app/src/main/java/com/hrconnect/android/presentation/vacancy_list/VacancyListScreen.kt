package com.hrconnect.android.presentation.vacancy_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.android.R
import com.hrconnect.android.domain.model.Vacancy
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import com.hrconnect.uikit.presentation.components.buttons.PrimaryButton
import com.hrconnect.uikit.presentation.components.buttons.SecondaryButton
import com.hrconnect.uikit.presentation.components.cards.ListCard
import com.hrconnect.uikit.presentation.components.inputs.Input
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun VacancyListRoot(
    viewModel: VacancyListViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    VacancyListScreen(
        state = state
    )
}

@Composable
fun VacancyListScreen(
    state: VacancyListState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(HrTheme.colorScheme.container)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Vacancies",
                    style = TextStyle(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        letterSpacing = 0.sp,
                        color = HrTheme.colorScheme.topBarTitle
                    )
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(
                            interactionSource = null,
                            indication = ripple(bounded = false)
                        ) {}
                ) {
                    Icon(
                        modifier = Modifier.size(20.1.dp, 20.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_settings),
                        contentDescription = null,
                        tint = Color(0xFF64748B)
                    )
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFE2E8F0)
            )
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 24.dp
            )
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SecondaryButton(
                        modifier = Modifier.weight(1f),
                        label = "Configure Tables",
                        onClick = {}
                    )
                    PrimaryButton(
                        modifier = Modifier.weight(1f),
                        label = "+ Create Vacancy",
                        onClick = {}
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
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
                        .background(
                            color = HrTheme.colorScheme.container,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    Input(
                        state = state.searchQueryState,
                        placeholder = "Search by title...",
                        leadingIcon = ImageVector.vectorResource(R.drawable.ic_search)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    PrimaryButton(
                        label = "Search",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${state.vacancies.size} vacancies found",
                            style = TextStyle(
                                fontFamily = Manrope,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                                color = HrTheme.colorScheme.secondary
                            )
                        )
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .border(
                                    width = 1.dp,
                                    color = HrTheme.colorScheme.border,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable {}
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                ),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(13.5.dp),
                                imageVector = ImageVector.vectorResource(R.drawable.ic_filter_list),
                                contentDescription = null,
                                tint = HrTheme.colorScheme.secondary
                            )
                            Text(
                                text = "Apply Filters",
                                style = TextStyle(
                                    fontFamily = Manrope,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = HrTheme.colorScheme.secondary
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.vacancies) { vacancy ->
                ListCard(
                    modifier = Modifier.padding(vertical = 16.dp),
                    title = vacancy.title,
                    company = vacancy.company,
                    employment = vacancy.employment,
                    minSalary = vacancy.minSalary / 1000,
                    maxSalary = vacancy.maxSalary / 1000,
                    applicantsCount = vacancy.applicantsCount,
                    isActive = true,
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun VacancyListScreenPreview() {
    HrTheme {
        VacancyListScreen(
            state = VacancyListState(
                vacancies = listOf(
                    Vacancy(
                        title = "Senior Product Designer",
                        company = "Product Team",
                        employment = "Full-time",
                        minSalary = 120000,
                        maxSalary = 160000,
                        applicantsCount = 24,
                        isActive = true
                    ),
                    Vacancy(
                        title = "Middle Backend Developer",
                        company = "Engineering",
                        employment = "Remote",
                        minSalary = 90000,
                        maxSalary = 130000,
                        applicantsCount = 15,
                        isActive = true
                    ),
                    Vacancy(
                        title = "QA Engineer",
                        company = "Engineering",
                        employment = "Full-time",
                        minSalary = 70000,
                        maxSalary = 100000,
                        applicantsCount = 8,
                        isActive = true
                    ),
                    Vacancy(
                        title = "Project Manager",
                        company = "Operations",
                        employment = "Hybrid",
                        minSalary = 110000,
                        maxSalary = 140000,
                        applicantsCount = 32,
                        isActive = true
                    )
                )
            )
        )
    }
}