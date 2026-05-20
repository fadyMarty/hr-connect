package com.hrconnect.android.domain.repository

interface DictionaryRepository {
    suspend fun getCities(): Result<Unit>
    suspend fun getStatuses(): Result<Unit>
    suspend fun getCandidateStatuses(): Result<Unit>
}