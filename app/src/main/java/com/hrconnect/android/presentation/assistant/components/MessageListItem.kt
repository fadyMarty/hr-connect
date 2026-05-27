package com.hrconnect.android.presentation.assistant.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.hrconnect.android.domain.model.ChatMessage
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun MessageListItem(
    message: ChatMessage,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val chatBubbleShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
        bottomStart = if (message.isFromUser) 20.dp else 4.dp,
        bottomEnd = if (message.isFromUser) 4.dp else 20.dp
    )

    if (message.error != null) {
        Button(
            onClick = onRetryClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = HrTheme.colorScheme.error
            )
        ) {
            Text(
                text = message.error
            )
        }
    } else {
        Column(
            modifier = modifier,
            horizontalAlignment = if (message.isFromUser) {
                Alignment.End
            } else Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
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
                        if (message.isFromUser) {
                            Modifier
                                .padding(horizontal = 16.dp)
                                .padding(
                                    top = 16.dp,
                                    bottom = 25.dp
                                )
                        } else {
                            Modifier
                                .border(
                                    width = 1.dp,
                                    color = HrTheme.colorScheme.border,
                                    shape = chatBubbleShape
                                )
                                .padding(horizontal = 17.dp)
                                .padding(
                                    top = 17.dp,
                                    bottom = 16.dp
                                )
                        }
                    )
            ) {
                Text(
                    text = message.content,
                    color = if (message.isFromUser) {
                        HrTheme.colorScheme.onPrimary
                    } else {
                        HrTheme.colorScheme.onBackground
                    }
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = formatTimestamp(message.timestamp),
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
}

private fun formatTimestamp(timestamp: Long): String {
    val instant = Instant.ofEpochMilli(timestamp)
    return DateTimeFormatter
        .ofPattern("hh:mm a")
        .withZone(ZoneId.systemDefault())
        .format(instant)
}

@Preview
@Composable
private fun MessageListItemPreview() {
    HrTheme {
        MessageListItem(
            message = ChatMessage(
                content = "I've analyzed the report. For\n" +
                        "technical roles, the average time-\n" +
                        "to-hire decreased by 12% this\n" +
                        "quarter, primarily due to the new\n" +
                        "automated screening workflow.\n" +
                        "Would you like me to generate a\n" +
                        "chart for this data?",
                isFromUser = true
            ),
            onRetryClick = {}
        )
    }
}