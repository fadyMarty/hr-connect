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
    data object PostLoginLoading : Route

    @Serializable
    data object HomeGraph : Route
}