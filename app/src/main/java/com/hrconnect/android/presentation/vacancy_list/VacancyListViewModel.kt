package com.hrconnect.android.presentation.vacancy_list

import androidx.lifecycle.ViewModel
import com.hrconnect.android.domain.repository.VacancyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VacancyListViewModel(
    private val vacancyRepository: VacancyRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(VacancyListState())
    val state = _state.asStateFlow()
}