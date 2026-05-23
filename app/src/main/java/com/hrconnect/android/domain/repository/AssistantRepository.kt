package com.hrconnect.android.domain.repository

import kotlinx.coroutines.flow.Flow

interface AssistantRepository {
    suspend fun initialize()
    fun sendMessage(text: String): Flow<String>
}