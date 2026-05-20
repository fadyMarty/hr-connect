package com.hrconnect.android.presentation.vacancy_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.hrconnect.android.presentation.navigation.Route
import logcat.logcat

class VacancyDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val vacancyId = savedStateHandle.toRoute<Route.VacancyDetail>().id

    init {
        logcat { vacancyId }
    }
}