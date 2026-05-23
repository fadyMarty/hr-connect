package com.hrconnect.android.data.repository

import android.content.Context
import com.google.ai.edge.litertlm.Contents
import com.google.ai.edge.litertlm.Conversation
import com.google.ai.edge.litertlm.ConversationConfig
import com.google.ai.edge.litertlm.Engine
import com.google.ai.edge.litertlm.EngineConfig
import com.hrconnect.android.common.util.Constants
import com.hrconnect.android.domain.repository.AssistantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File

class LiteRtAssistantRepository(
    private val context: Context,
) : AssistantRepository {

    private lateinit var engine: Engine
    private lateinit var conversation: Conversation

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
            val engineConfig = EngineConfig(
                modelPath = modelFile.absolutePath
            )
            engine = Engine(engineConfig)
            engine.initialize()
            val conversationConfig = ConversationConfig(
                systemInstruction = Contents.of("You are a helpful HR assistant.")
            )
            conversation = engine.createConversation(conversationConfig)
        }
    }

    override fun sendMessage(text: String): Flow<String> {
        return conversation.sendMessageAsync(text).map {
            it.toString()
        }
    }

    override fun close() {
        conversation.close()
        engine.close()
    }
}