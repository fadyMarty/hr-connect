package com.hrconnect.android.data.repository

import com.hrconnect.android.data.util.safeCall
import com.hrconnect.android.domain.model.Vacancy
import com.hrconnect.android.domain.repository.VacancyRepository

class VacancyRepositoryImpl : VacancyRepository {

    private val vacancies = mutableListOf(
        Vacancy(
            title = "Senior Product Designer",
            company = "Product Team",
            employment = "Full-time",
            minSalary = 120000,
            maxSalary = 160000,
            applicantsCount = 24,
            isActive = true
        ),
        Vacancy(
            title = "Middle Backend Developer",
            company = "Engineering",
            employment = "Remote",
            minSalary = 90000,
            maxSalary = 130000,
            applicantsCount = 15,
            isActive = true
        ),
        Vacancy(
            title = "QA Engineer",
            company = "Engineering",
            employment = "Full-time",
            minSalary = 70000,
            maxSalary = 100000,
            applicantsCount = 8,
            isActive = true
        ),
        Vacancy(
            title = "Project Manager",
            company = "Operations",
            employment = "Hybrid",
            minSalary = 110000,
            maxSalary = 140000,
            applicantsCount = 32,
            isActive = true
        )
    )

    override suspend fun getVacancies(): Result<List<Vacancy>> {
        return safeCall { vacancies }
    }
}