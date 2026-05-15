package com.hrconnect.android.domain.repository

import com.hrconnect.android.domain.model.Vacancy

interface VacancyRepository {
    suspend fun getVacancies(): Result<List<Vacancy>>
}