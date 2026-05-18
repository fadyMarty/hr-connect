package com.hrconnect.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.hrconnect.android.presentation.navigation.NavigationRoot
import com.hrconnect.android.presentation.navigation.Route
import com.hrconnect.android.presentation.splash.SplashViewModel
import com.hrconnect.uikit.common.theme.HrTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.state.value.isLoading
        }
        enableEdgeToEdge()
        setContent {
            HrTheme {
                val state by viewModel.state.collectAsStateWithLifecycle()
                val navController = rememberNavController()

                if (!state.isLoading) {
                    NavigationRoot(
                        navController = navController,
                        startDestination = if (state.isLoggedIn) {
                            Route.HomeGraph
                        } else {
                            Route.AuthGraph
                        }
                    )
                }
            }
        }
    }
}