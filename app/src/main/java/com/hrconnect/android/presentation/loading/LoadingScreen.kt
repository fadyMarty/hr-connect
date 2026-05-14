package com.hrconnect.android.presentation.loading

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrconnect.android.R
import com.hrconnect.android.presentation.util.ObserveAsEvents
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.common.theme.Manrope
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoadingRoot(
    viewModel: LoadingViewModel = koinViewModel(),
    onLoadingSuccess: () -> Unit,
) {
    val progress by viewModel.progress.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            LoadingEvent.Success -> onLoadingSuccess()
        }
    }

    LoadingScreen(
        progress = progress
    )
}

@Composable
fun LoadingScreen(
    progress: Float,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        color = HrTheme.colorScheme.primaryVariant,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(28.5.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_bubble_chart),
                    contentDescription = null,
                    tint = HrTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "HR Connect",
                style = HrTheme.typography.screenHeader
            )
            Spacer(modifier = Modifier.height(32.dp))
            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = HrTheme.colorScheme.primaryVariant,
                trackColor = HrTheme.colorScheme.indicatorTrack,
                gapSize = (-2).dp,
                drawStopIndicator = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Setting up your workspace...",
                style = TextStyle(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp,
                    color = HrTheme.colorScheme.secondary
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Synchronizing talent data and active vacancies",
                style = TextStyle(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp,
                    color = HrTheme.colorScheme.description,
                )
            )
        }
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    HrTheme {
        LoadingScreen(
            progress = 0.3f
        )
    }
}