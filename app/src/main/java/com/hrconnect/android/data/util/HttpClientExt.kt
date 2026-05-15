package com.hrconnect.android.data.util

suspend fun <T> safeCall(
    execute: suspend () -> T,
): Result<T> {
    return try {
        val response = execute()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
}