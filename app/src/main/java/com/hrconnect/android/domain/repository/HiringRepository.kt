package com.hrconnect.android.domain.repository

interface HiringRepository {
    suspend fun getDepartments(): Result<Unit>
    suspend fun getPositions(): Result<Unit>
    suspend fun getHiringVacancies(): Result<Unit>
}