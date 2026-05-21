package com.hrconnect.android.domain.repository

import io.github.vinceglb.filekit.PlatformFile

interface VacancyRepository {
    suspend fun getVacancies(): Result<Unit>
    suspend fun createVacancy(): Result<Unit>
    suspend fun getVacancyById(id: String): Result<Unit>
    suspend fun updateVacancy(id: String): Result<Unit>
    suspend fun deleteVacancy(id: String): Result<Unit>
    suspend fun uploadVacancyFiles(id: String, files: List<PlatformFile>): Result<Unit>
    suspend fun getVacanciesShort(): Result<Unit>
}