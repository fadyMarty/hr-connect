package com.hrconnect.android.data.repository

import android.content.Context
import com.hrconnect.android.common.util.Constants
import com.hrconnect.android.domain.repository.AssistantRepository
import com.llamatik.library.platform.LlamaBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import java.io.File

class LlamatikAssistantRepository(
    private val context: Context,
) : AssistantRepository {

    override suspend fun initialize() {
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

    override fun sendMessage(text: String): Flow<String> {
        return callbackFlow {
            LlamaBridge.generateWithContextStream(
                system = "You are a helpful HR assistant.",
                context = "",
                user = text,
                onDelta = { text ->
                    trySend(text)
                },
                onDone = {
                    close()
                },
                onError = { error ->
                    close(Exception(error))
                }
            )
            awaitClose()
        }
    }
}