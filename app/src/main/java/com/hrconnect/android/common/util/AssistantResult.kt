package com.hrconnect.android.common.util

sealed interface AssistantResult {
    data class Delta(val text: String) : AssistantResult
    data class Error(val message: String) : AssistantResult
}