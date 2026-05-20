package com.hrconnect.android.presentation.vacancy_detail

sealed interface VacancyDetailEvent {
    data object OnBackClick : VacancyDetailEvent
}