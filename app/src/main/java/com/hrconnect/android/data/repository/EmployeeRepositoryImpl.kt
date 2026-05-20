package com.hrconnect.android.data.repository

import com.hrconnect.android.domain.repository.EmployeeRepository
import com.hrconnect.netlib.common.util.safeCall
import com.hrconnect.netlib.data.remote.EmployeeApi

class EmployeeRepositoryImpl(
    private val employeeApi: EmployeeApi,
) : EmployeeRepository {

    override suspend fun getApplicants(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Список соискателей"
        ) {
            employeeApi.getApplicants()
        }
    }

    override suspend fun getEmployeeById(id: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Сотрудник по ID"
        ) {
            employeeApi.getEmployeeById(id)
        }
    }

    override suspend fun getShortStaff(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Краткий список HR-специалистов"
        ) {
            employeeApi.getShortStaff()
        }
    }

    companion object {
        private const val TAG = "EmployeeRepositoryImpl"
    }
}