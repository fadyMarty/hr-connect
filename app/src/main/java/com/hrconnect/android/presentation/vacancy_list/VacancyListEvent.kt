package com.hrconnect.android.presentation.vacancy_list

import com.hrconnect.android.domain.model.Vacancy

sealed interface VacancyListEvent {
    data object OnCreateVacancyClick : VacancyListEvent
    data class OnVacancyClick(val vacancy: Vacancy) : VacancyListEvent
}