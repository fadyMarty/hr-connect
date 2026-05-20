package com.hrconnect.android.presentation.vacancy_list

import androidx.compose.foundation.text.input.TextFieldState
import com.hrconnect.android.domain.model.Vacancy

data class VacancyListState(
    val searchQueryState: TextFieldState = TextFieldState(),
    val vacancies: List<Vacancy> = listOf(
        Vacancy(
            title = "Senior Product Designer",
            department = "Product Team",
            employment = "Full-time",
            minSalary = 120000,
            maxSalary = 160000,
            applicantsCount = 24,
            isActive = true
        ),
        Vacancy(
            title = "Middle Backend Developer",
            department = "Engineering",
            employment = "Remote",
            minSalary = 90000,
            maxSalary = 130000,
            applicantsCount = 15,
            isActive = true
        ),
        Vacancy(
            title = "QA Engineer",
            department = "Engineering",
            employment = "Full-time",
            minSalary = 70000,
            maxSalary = 100000,
            applicantsCount = 8,
            isActive = true
        ),
        Vacancy(
            title = "Project Manager",
            department = "Operations",
            employment = "Hybrid",
            minSalary = 110000,
            maxSalary = 140000,
            applicantsCount = 32,
            isActive = true
        )
    ),
)