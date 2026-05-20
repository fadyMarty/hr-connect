package com.hrconnect.android.domain.repository

import com.hrconnect.android.common.util.AssistantResult
import kotlinx.coroutines.flow.Flow

interface AssistantRepository {
    suspend fun init()
    fun generateStream(prompt: String): Flow<AssistantResult>
    fun shutdown()
}