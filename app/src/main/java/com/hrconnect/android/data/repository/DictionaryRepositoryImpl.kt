package com.hrconnect.android.data.repository

import com.hrconnect.android.domain.repository.DictionaryRepository
import com.hrconnect.netlib.common.util.safeCall
import com.hrconnect.netlib.data.remote.DictionaryApi

class DictionaryRepositoryImpl(
    private val dictionaryApi: DictionaryApi,
) : DictionaryRepository {

    override suspend fun getCities(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Города"
        ) {
            dictionaryApi.getCities()
        }
    }

    override suspend fun getStatuses(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Статусы соискателей"
        ) {
            dictionaryApi.getStatuses()
        }
    }

    override suspend fun getCandidateStatuses(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Статусы HR доски"
        ) {
            dictionaryApi.getCandidateStatuses()
        }
    }

    companion object {
        private const val TAG = "DictionaryRepositoryImp"
    }
}