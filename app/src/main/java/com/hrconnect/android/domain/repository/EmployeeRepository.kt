package com.hrconnect.android.domain.repository

interface EmployeeRepository {
    suspend fun getApplicants(): Result<Unit>
    suspend fun getEmployeeById(id: String): Result<Unit>
    suspend fun getShortStaff(): Result<Unit>
}