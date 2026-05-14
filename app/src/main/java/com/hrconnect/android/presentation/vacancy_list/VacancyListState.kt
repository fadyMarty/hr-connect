package com.hrconnect.android.presentation.vacancy_list

import androidx.compose.foundation.text.input.TextFieldState
import com.hrconnect.android.domain.model.Vacancy

data class VacancyListState(
    val searchQueryState: TextFieldState = TextFieldState(),
    val vacancies: List<Vacancy> = emptyList(),
)