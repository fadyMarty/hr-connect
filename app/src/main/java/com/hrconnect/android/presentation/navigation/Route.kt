package com.hrconnect.android.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object AuthGraph : Route

    @Serializable
    data object Register : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object Loading : Route

    @Serializable
    data object HomeGraph : Route

    @Serializable
    data object VacancyList : Route

    @Serializable
    data object CandidateList : Route

    @Serializable
    data object Profile : Route
}