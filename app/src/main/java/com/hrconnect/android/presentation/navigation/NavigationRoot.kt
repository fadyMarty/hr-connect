package com.hrconnect.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.hrconnect.android.presentation.assistant.AssistantRoot
import com.hrconnect.android.presentation.create_vacancy.CreateVacancyRoot
import com.hrconnect.android.presentation.home.HomeGraph
import com.hrconnect.android.presentation.loading.LoadingRoot
import com.hrconnect.android.presentation.login.LoginRoot
import com.hrconnect.android.presentation.register.RegisterRoot

@Composable
fun NavigationRoot(
    navController: NavHostController,
    startDestination: Any,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation<Route.AuthGraph>(
            startDestination = Route.Register
        ) {
            composable<Route.Register> {
                RegisterRoot(
                    onLoginClick = {
                        navController.navigate(Route.Login)
                    }
                )
            }
            composable<Route.Login> {
                LoginRoot(
                    onLoginSuccess = {
                        navController.navigate(Route.Loading) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Route.Loading> {
                LoadingRoot(
                    onLoadingSuccess = {
                        navController.navigate(Route.HomeGraph) {
                            popUpTo(Route.AuthGraph) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable<Route.HomeGraph> {
            HomeGraph(
                onCreateVacancyClick = {
                    navController.navigate(Route.CreateVacancy)
                },
                onAssistantClick = {
                    navController.navigate(Route.Assistant)
                }
            )
        }
        composable<Route.Assistant> {
            AssistantRoot(
                onCloseClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Route.CreateVacancy> {
            CreateVacancyRoot(
                onCloseClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}