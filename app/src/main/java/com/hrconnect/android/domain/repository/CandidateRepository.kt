package com.hrconnect.android.domain.repository

interface CandidateRepository {
    suspend fun getCandidateVacancies(): Result<Unit>
    suspend fun getCardComment(cardId: String): Result<Unit>
    suspend fun updateCardComment(cardId: String): Result<Unit>
    suspend fun updateHrCard(cardId: String): Result<Unit>
    suspend fun updateCandidateStatus(): Result<Unit>
    suspend fun deleteCard(cardId: String): Result<Unit>
}