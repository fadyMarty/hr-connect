package com.hrconnect.android.presentation.hr_board

import androidx.compose.foundation.text.input.TextFieldState

data class HrBoardState(
    val showFilterDialog: Boolean = false,
    val vacancyState: TextFieldState = TextFieldState(),
    val statuses: List<String> = emptyList(),
    val department: String? = "Design",
    val cityState: TextFieldState = TextFieldState(),
    val salaryRange: ClosedFloatingPointRange<Float> = 0f..200_000f,
)
