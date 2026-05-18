package com.hrconnect.android.data.repository

import android.content.Context
import com.hrconnect.android.common.Constants
import com.hrconnect.android.data.util.AssistantResult
import com.hrconnect.android.domain.repository.AssistantRepository
import com.llamatik.library.platform.LlamaBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.File


class AssistantRepositoryImpl(
    private val context: Context,
) : AssistantRepository {

    override suspend fun init() {
        withContext(Dispatchers.IO) {
            val modelFile = File(context.filesDir, Constants.MODEL_FILE_NAME)
            if (!modelFile.exists()) {
                context.assets.open(Constants.MODEL_FILE_NAME).use { inputStream ->
                    modelFile.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
            }
            LlamaBridge.initGenerateModel(modelFile.absolutePath)
        }
    }

    override fun generateStream(prompt: String): Flow<AssistantResult> {
        return callbackFlow {
            LlamaBridge.generateWithContextStream(
                system = "You are a helpful HR assistant.",
                context = "",
                user = prompt,
                onDelta = { text ->
                    trySend(AssistantResult.Delta(text))
                },
                onDone = {
                    close()
                },
                onError = { message ->
                    trySend(AssistantResult.Error(message))
                }
            )
            awaitClose {}
        }.flowOn(Dispatchers.IO)
    }

    override fun shutdown() {
        LlamaBridge.shutdown()
    }
}