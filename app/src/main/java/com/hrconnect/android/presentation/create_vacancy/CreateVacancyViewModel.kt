package com.hrconnect.android.presentation.create_vacancy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateVacancyViewModel : ViewModel() {

    private val _state = MutableStateFlow(CreateVacancyState())
    val state = _state.asStateFlow()

    fun onEvent(event: CreateVacancyEvent) {
        when (event) {
            is CreateVacancyEvent.OnDepartmentSelected -> {
                _state.update {
                    it.copy(
                        department = event.department
                    )
                }
            }
            is CreateVacancyEvent.OnPositionSelected -> {
                _state.update {
                    it.copy(
                        position = event.position
                    )
                }
            }
            is CreateVacancyEvent.OnPublicVacancyChecked -> {
                _state.update {
                    it.copy(
                        publicVacancy = event.checked
                    )
                }
            }
            else -> Unit
        }
    }
}