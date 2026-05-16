package com.hrconnect.android.data.util

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

suspend fun <T> safeCall(
    execute: suspend () -> T,
): Result<T> {
    return try {
        val response = execute()
        Result.success(response)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        Result.failure(e)
    }
}