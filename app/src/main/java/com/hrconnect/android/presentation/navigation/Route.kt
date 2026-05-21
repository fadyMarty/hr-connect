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
    data object HrBoard : Route

    @Serializable
    data object CandidateGraph : Route

    @Serializable
    data object CandidateList : Route

    @Serializable
    data object CandidateDetail : Route

    @Serializable
    data object VacancyGraph : Route

    @Serializable
    data object VacancyList : Route

    @Serializable
    data class VacancyDetail(val id: String) : Route

    @Serializable
    data object CreateVacancy : Route

    @Serializable
    data object Assistant : Route
}