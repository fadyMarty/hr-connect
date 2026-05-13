package com.hrconnect.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.hrconnect.android.presentation.register.RegisterRoot

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.AuthGraph
    ) {
        navigation<Route.AuthGraph>(
            startDestination = Route.Register
        ) {
            composable<Route.Register> {
                RegisterRoot(
                    onRegisterSuccess = {
                        navController.navigate(Route.HomeGraph) {
                            popUpTo(Route.AuthGraph) {
                                inclusive = true
                            }
                        }
                    },
                    onLoginClick = {
                        navController.navigate(Route.Login)
                    }
                )
            }
        }
    }
}