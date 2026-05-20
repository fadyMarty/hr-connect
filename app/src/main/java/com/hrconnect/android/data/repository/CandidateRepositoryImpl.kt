package com.hrconnect.android.data.repository

import com.hrconnect.android.domain.repository.CandidateRepository
import com.hrconnect.netlib.common.util.safeCall
import com.hrconnect.netlib.data.remote.CandidateApi

class CandidateRepositoryImpl(
    private val candidateApi: CandidateApi,
) : CandidateRepository {

    override suspend fun getCandidateVacancies(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Список соискателей HR доски"
        ) {
            candidateApi.getCandidateVacancies()
        }
    }

    override suspend fun getCardComment(cardId: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Комментарий карточки"
        ) {
            candidateApi.getCandidateVacancies()
        }
    }

    override suspend fun updateCardComment(cardId: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Обновление комментария"
        ) {
            candidateApi.updateCardComment(cardId)
        }
    }

    override suspend fun updateHrCard(cardId: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Обновление HR карточки"
        ) {
            candidateApi.updateHrCard(cardId)
        }
    }

    override suspend fun updateCandidateStatus(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Обновление статуса соискателя"
        ) {
            candidateApi.updateCandidateStatus()
        }
    }

    override suspend fun deleteCard(cardId: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Удаление карточки с доски"
        ) {
            candidateApi.deleteCard(cardId)
        }
    }

    companion object {
        private const val TAG = "CandidateRepositoryImpl"
    }
}