package com.hrconnect.android.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.hrconnect.android.R
import com.hrconnect.android.presentation.navigation.Route
import com.hrconnect.android.presentation.vacancy_detail.VacancyDetailRoot
import com.hrconnect.android.presentation.vacancy_list.VacancyListRoot
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.presentation.components.bottom_bar.BottomBar
import com.hrconnect.uikit.presentation.components.bottom_bar.BottomBarItem

@Composable
fun HomeGraph(
    onCreateVacancyClick: () -> Unit,
    onAssistantClick: () -> Unit,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomBar(
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                items = listOf(
                    BottomBarItem(
                        selected = currentDestination.isSelected(Route.HrBoard),
                        icon = ImageVector.vectorResource(R.drawable.ic_dashboard),
                        label = "Board",
                        route = Route.HrBoard
                    ),
                    BottomBarItem(
                        selected = currentDestination.isSelected(Route.CandidateList),
                        icon = ImageVector.vectorResource(R.drawable.ic_groups),
                        label = "Candidates",
                        route = Route.CandidateList
                    ),
                    BottomBarItem(
                        selected = currentDestination.isSelected(Route.VacancyGraph),
                        icon = ImageVector.vectorResource(R.drawable.ic_work),
                        label = "Vacancies",
                        route = Route.VacancyGraph
                    )
                ),
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = Route.HrBoard
            ) {
                composable<Route.HrBoard> {

                }
                composable<Route.CandidateList> {

                }
                navigation<Route.VacancyGraph>(
                    startDestination = Route.VacancyList
                ) {
                    composable<Route.VacancyList> {
                        VacancyListRoot(
                            onCreateVacancyClick = onCreateVacancyClick,
                            onVacancyClick = { vacancy ->
                                navController.navigate(
                                    Route.VacancyDetail(vacancy.id)
                                )
                            }
                        )
                    }
                    composable<Route.VacancyDetail> {
                        VacancyDetailRoot(
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = 20.dp,
                        bottom = 40.5.dp
                    )
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(HrTheme.colorScheme.primaryVariant)
                    .clickable(onClick = onAssistantClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(25.67.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_assistant),
                    contentDescription = null,
                    tint = HrTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

private fun NavDestination?.isSelected(route: Route): Boolean {
    return this?.hierarchy?.any {
        it.route == route::class.qualifiedName
    } == true
}