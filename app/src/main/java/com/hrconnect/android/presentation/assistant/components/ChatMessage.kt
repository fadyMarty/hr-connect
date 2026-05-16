package com.hrconnect.android.presentation.assistant.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrconnect.android.domain.model.Message
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import java.time.format.DateTimeFormatter

@Composable
fun ChatMessage(
    message: Message,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    val chatBubbleShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
        bottomStart = if (message.isFromUser) 20.dp else 4.dp,
        bottomEnd = if (message.isFromUser) 4.dp else 20.dp
    )

    Column(
        modifier = modifier,
        horizontalAlignment = if (message.isFromUser) {
            Alignment.End
        } else Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Column(
            modifier = Modifier
                .then(
                    if (message.isFromUser) {
                        Modifier.dropShadow(
                            shape = chatBubbleShape,
                            shadow = Shadow(
                                offset = DpOffset(0.dp, 1.dp),
                                radius = 2.dp,
                                alpha = 0.05f
                            )
                        )
                    } else {
                        Modifier.dropShadow(
                            shape = chatBubbleShape,
                            shadow = Shadow(
                                offset = DpOffset(0.dp, 4.dp),
                                radius = 12.dp,
                                alpha = 0.05f
                            )
                        )
                    }
                )
                .background(
                    color = if (message.isFromUser) {
                        HrTheme.colorScheme.primary
                    } else {
                        HrTheme.colorScheme.container
                    },
                    shape = chatBubbleShape
                )
                .then(
                    if (!message.isFromUser) {
                        Modifier.border(
                            width = 1.dp,
                            color = HrTheme.colorScheme.border,
                            shape = chatBubbleShape
                        )
                    } else Modifier
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (isLoading) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(
                                        color = HrTheme.colorScheme.primary,
                                        shape = CircleShape
                                    )
                            )
                        }
                    }
                    Text(
                        text = "Inference in progress...",
                        style = HrTheme.typography.bodyMedium,
                        color = HrTheme.colorScheme.fieldLabel
                    )
                }
            }
            if (message.content.isBlank()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(201.67.dp)
                            .height(16.dp)
                            .background(
                                color = HrTheme.colorScheme.containerBorder,
                                shape = CircleShape
                            )
                    )
                    Box(
                        modifier = Modifier
                            .width(151.25.dp)
                            .height(16.dp)
                            .background(
                                color = HrTheme.colorScheme.containerBorder,
                                shape = CircleShape
                            )
                    )
                }
            } else {
                Text(
                    text = message.content,
                    color = if (message.isFromUser) {
                        HrTheme.colorScheme.onPrimary
                    } else {
                        HrTheme.colorScheme.onBackground
                    }
                )
            }
        }
        Text(
            text = DateTimeFormatter.ofPattern("hh:mm a").format(message.createdAt),
            style = TextStyle(
                fontFamily = Manrope,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.sp,
                color = HrTheme.colorScheme.fieldLabel
            )
        )
    }
}

@Preview
@Composable
private fun ChatMessagePreview() {
    HrTheme {
        ChatMessage(
            message = Message(
                content = "",
                isFromUser = false
            ),
            isLoading = true
        )
    }
}