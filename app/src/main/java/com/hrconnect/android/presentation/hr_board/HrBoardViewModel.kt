package com.hrconnect.android.presentation.hr_board

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HrBoardViewModel : ViewModel() {

    private val _state = MutableStateFlow(HrBoardState())
    val state = _state.asStateFlow()

    fun onEvent(event: HrBoardEvent) {
        when (event) {
            HrBoardEvent.OnFilterMenuClick -> {
                _state.update {
                    it.copy(
                        showFilterDialog = true
                    )
                }
            }
            HrBoardEvent.OnDismissFilterDialog -> {
                _state.update {
                    it.copy(
                        showFilterDialog = false
                    )
                }
            }
            is HrBoardEvent.OnStatusSelected -> {
                _state.update {
                    val isSelected = it.statuses.contains(event.status)
                    it.copy(
                        statuses = if (isSelected) {
                            it.statuses - event.status
                        } else {
                            it.statuses + event.status
                        }
                    )
                }
            }
            is HrBoardEvent.OnDepartmentSelected -> {
                _state.update {
                    it.copy(
                        department = event.department
                    )
                }
            }
            is HrBoardEvent.OnResetClick -> {
                _state.update {
                    it.copy(
                        statuses = emptyList(),
                        department = null,
                        salaryRange = 0f..200000f
                    )
                }
                state.value.vacancyState.clearText()
                state.value.cityState.clearText()
            }
            is HrBoardEvent.OnSalaryRangeChanged -> {
                _state.update {
                    it.copy(
                        salaryRange = event.salaryRange
                    )
                }
            }
            HrBoardEvent.OnApplyFiltersClick -> {}
        }
    }
}