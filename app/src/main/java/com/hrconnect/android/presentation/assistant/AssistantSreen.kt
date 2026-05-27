package com.hrconnect.android.presentation.assistant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.android.R
import com.hrconnect.android.presentation.assistant.components.AssistantAction
import com.hrconnect.android.presentation.assistant.components.MessageListItem
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AssistantRoot(
    viewModel: AssistantViewModel = koinViewModel(),
    onCloseClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AssistantScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                AssistantEvent.OnCloseClick -> onCloseClick()
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@Composable
fun AssistantScreen(
    state: AssistantState,
    onEvent: (AssistantEvent) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .dropShadow(
                        shape = RectangleShape,
                        shadow = Shadow(
                            offset = DpOffset(0.dp, 1.dp),
                            radius = 2.dp,
                            alpha = 0.05f
                        )
                    )
                    .background(HrTheme.colorScheme.container)
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .height(64.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .windowInsetsPadding(WindowInsets.displayCutout)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            painter = painterResource(R.drawable.img_ai_assistant_avatar),
                            contentDescription = null
                        )
                        Text(
                            text = "AI Assistant",
                            style = TextStyle(
                                fontFamily = Manrope,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                lineHeight = 28.sp,
                                letterSpacing = 0.sp
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable {
                                onEvent(AssistantEvent.OnCloseClick)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(14.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.ic_close),
                            contentDescription = null,
                            tint = Color(0xFF64748B)
                        )
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = HrTheme.colorScheme.bottomBarBorder
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 16.dp,
                        end = 20.dp,
                        bottom = 19.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    reverseLayout = true
                ) {
                    if (state.messages.isEmpty()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .dropShadow(
                                        shape = RoundedCornerShape(24.dp),
                                        shadow = Shadow(
                                            offset = DpOffset(0.dp, 4.dp),
                                            radius = 12.dp,
                                            alpha = 0.05f
                                        )
                                    )
                                    .background(
                                        color = HrTheme.colorScheme.container,
                                        shape = RoundedCornerShape(24.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = HrTheme.colorScheme.indicatorTrack.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(24.dp)
                                    )
                                    .padding(25.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .rotate(-3f)
                                        .size(80.dp)
                                        .dropShadow(
                                            shape = RoundedCornerShape(24.dp),
                                            shadow = Shadow(
                                                offset = DpOffset(0.dp, 4.dp),
                                                radius = 6.dp,
                                                spread = (-4).dp,
                                                alpha = 0.1f
                                            )
                                        )
                                        .dropShadow(
                                            shape = RoundedCornerShape(24.dp),
                                            shadow = Shadow(
                                                offset = DpOffset(0.dp, 10.dp),
                                                radius = 15.dp,
                                                spread = (-3).dp,
                                                alpha = 0.1f
                                            )
                                        )
                                        .background(
                                            color = HrTheme.colorScheme.primaryVariant,
                                            shape = RoundedCornerShape(24.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        modifier = Modifier.size(36.67.dp),
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_assistant),
                                        contentDescription = null,
                                        tint = HrTheme.colorScheme.onPrimary
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Hello, HR Team",
                                    style = HrTheme.typography.screenHeader,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 3.84.dp),
                                    text = "I'm your intelligent recruitment co-pilot.How can I help streamline your workflow today?",
                                    textAlign = TextAlign.Center,
                                    color = HrTheme.colorScheme.fieldLabel
                                )
                                Spacer(modifier = Modifier.height(32.dp))
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    AssistantAction(
                                        title = "Summarize profile",
                                        description = "Key skills & fit analysis",
                                        icon = ImageVector.vectorResource(R.drawable.ic_file)
                                    )
                                    AssistantAction(
                                        title = "Draft a job description",
                                        description = "Tailored to your role specs",
                                        icon = ImageVector.vectorResource(R.drawable.ic_edit_note)
                                    )
                                    AssistantAction(
                                        title = "Write outreach email",
                                        description = "Write outreach email",
                                        icon = ImageVector.vectorResource(R.drawable.ic_mail)
                                    )
                                    AssistantAction(
                                        title = "Interview questions",
                                        description = "Based on role requirements",
                                        icon = ImageVector.vectorResource(R.drawable.ic_quiz)
                                    )
                                }
                            }
                        }
                    }
                    itemsIndexed(state.messages) { index, message ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = if (message.isFromUser) 25.dp else 0.dp,
                                    end = if (message.isFromUser) 0.dp else 25.dp
                                ),
                            horizontalAlignment = if (message.isFromUser) {
                                Alignment.End
                            } else Alignment.Start
                        ) {
                            MessageListItem(
                                message = message,
                                onRetryClick = {
                                    onEvent(AssistantEvent.OnRetryClick(index))
                                }
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .dropShadow(
                            shape = RoundedCornerShape(
                                topStart = 24.dp,
                                topEnd = 24.dp
                            ),
                            shadow = Shadow(
                                offset = DpOffset(0.dp, (-8).dp),
                                radius = 30.dp,
                                alpha = 0.08f
                            )
                        )
                        .background(
                            color = HrTheme.colorScheme.container.copy(alpha = 0.8f),
                            shape = RoundedCornerShape(
                                topStart = 24.dp,
                                topEnd = 24.dp
                            )
                        )
                        .border(
                            width = 1.dp,
                            color = HrTheme.colorScheme.bottomBarBorder,
                            shape = RoundedCornerShape(
                                topStart = 24.dp,
                                topEnd = 24.dp
                            )
                        )
                        .padding(horizontal = 16.dp)
                        .padding(top = 19.dp, bottom = 18.dp)
                ) {
                    BasicTextField(
                        state = state.messageState,
                        lineLimits = TextFieldLineLimits.MultiLine(
                            maxHeightInLines = 4
                        ),
                        textStyle = TextStyle(
                            fontFamily = Manrope,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            lineHeight = 1.em,
                            letterSpacing = 0.sp,
                            color = HrTheme.colorScheme.onBackground
                        ),
                        cursorBrush = SolidColor(HrTheme.colorScheme.primary),
                        decorator = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(0.6f)
                                    .background(
                                        color = HrTheme.colorScheme.inputContainerDisabled,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = HrTheme.colorScheme.border,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable(
                                            interactionSource = null,
                                            indication = ripple(bounded = false),
                                            onClick = {}
                                        ),
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_attachment),
                                    contentDescription = null,
                                    tint = HrTheme.colorScheme.description
                                )
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(
                                            horizontal = 12.dp,
                                            vertical = 9.dp
                                        ),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    if (state.messageState.text.isEmpty()) {
                                        Text(
                                            text = "AI is thinking...",
                                            style = TextStyle(
                                                fontFamily = Manrope,
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 16.sp,
                                                lineHeight = 1.em,
                                                letterSpacing = 0.sp,
                                                color = HrTheme.colorScheme.onBackground
                                            ),
                                            color = HrTheme.colorScheme.description
                                        )
                                    }
                                    innerTextField()
                                }
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(HrTheme.colorScheme.indicatorTrack)
                                        .clickable(
                                            enabled = state.messageState.text.isNotBlank()
                                        ) {
                                            onEvent(AssistantEvent.OnSendMessageClick)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        modifier = Modifier.size(19.dp, 16.dp),
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_send),
                                        contentDescription = null,
                                        tint = HrTheme.colorScheme.description
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AssistantScreenPreview() {
    HrTheme {
        AssistantScreen(
            state = AssistantState(),
            onEvent = {}
        )
    }
}