package com.hrconnect.android.presentation.create_vacancy

sealed interface CreateVacancyEvent {
    data object OnCloseClick : CreateVacancyEvent
    data class OnDepartmentSelected(val department: String) : CreateVacancyEvent
    data class OnPositionSelected(val position: String) : CreateVacancyEvent
    data class OnPublicVacancyChecked(val checked: Boolean) : CreateVacancyEvent
}