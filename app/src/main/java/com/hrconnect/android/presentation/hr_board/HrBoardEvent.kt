package com.hrconnect.android.presentation.hr_board

sealed interface HrBoardEvent {
    data object OnFilterMenuClick : HrBoardEvent
    data object OnDismissFilterDialog : HrBoardEvent
    data class OnStatusSelected(val status: String) : HrBoardEvent
    data class OnDepartmentSelected(val department: String?) : HrBoardEvent
    data class OnSalaryRangeChanged(
        val salaryRange: ClosedFloatingPointRange<Float>,
    ) : HrBoardEvent
    data object OnResetClick : HrBoardEvent
    data object OnApplyFiltersClick : HrBoardEvent
}