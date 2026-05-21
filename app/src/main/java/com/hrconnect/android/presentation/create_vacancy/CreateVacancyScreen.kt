package com.hrconnect.android.presentation.create_vacancy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.android.presentation.create_vacancy.components.CreateVacancyBottomBar
import com.hrconnect.android.presentation.create_vacancy.components.CreateVacancyFirstStep
import com.hrconnect.android.presentation.create_vacancy.components.CreateVacancySecondStep
import com.hrconnect.android.presentation.create_vacancy.components.CreateVacancyTopBar
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.presentation.components.progress_bar.ProgressBar
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateVacancyRoot(
    viewModel: CreateVacancyViewModel = koinViewModel(),
    onCloseClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CreateVacancyScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                CreateVacancyEvent.OnCloseClick -> onCloseClick()
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@Composable
fun CreateVacancyScreen(
    state: CreateVacancyState,
    onEvent: (CreateVacancyEvent) -> Unit,
) {
    val pagerState = rememberPagerState { 3 }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            CreateVacancyTopBar(
                modifier = Modifier
                    .windowInsetsPadding(
                        WindowInsets.statusBars.union(WindowInsets.displayCutout)
                    ),
                onCloseClick = {
                    onEvent(CreateVacancyEvent.OnCloseClick)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = when (pagerState.currentPage) {
                            0 -> "General Information"
                            1 -> "Step 2: Details & Requirements"
                            else -> "Contact Details"
                        },
                        style = HrTheme.typography.fieldLabel,
                        color = HrTheme.colorScheme.primary
                    )
                    Text(
                        text = "Step ${pagerState.currentPage + 1} of ${pagerState.pageCount}",
                        style = HrTheme.typography.bodySmall,
                        color = HrTheme.colorScheme.description
                    )
                }
                ProgressBar(
                    modifier = Modifier,
                    currentPage = pagerState.currentPage,
                    pageCount = pagerState.pageCount
                )
            }
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                state = pagerState
            ) { index ->
                when (index) {
                    0 -> CreateVacancyFirstStep(
                        state = state,
                        onEvent = onEvent
                    )
                    1 -> CreateVacancySecondStep(
                        state = state,
                        onEvent = onEvent
                    )
                }
            }
            CreateVacancyBottomBar(
                onSaveDraftClick = {},
                onContinueClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateVacancyScreenPreview() {
    HrTheme {
        CreateVacancyScreen(
            state = CreateVacancyState(),
            onEvent = {}
        )
    }
}