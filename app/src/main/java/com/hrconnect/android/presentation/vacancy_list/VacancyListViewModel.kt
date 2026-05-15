package com.hrconnect.android.presentation.vacancy_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.android.domain.repository.VacancyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VacancyListViewModel(
    private val vacancyRepository: VacancyRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(VacancyListState())
    val state = _state.asStateFlow()

    init {
        getVacancies()
    }

    private fun getVacancies() {
        viewModelScope.launch {
            vacancyRepository.getVacancies()
                .onSuccess { vacancies ->
                    _state.update {
                        it.copy(
                            vacancies = vacancies
                        )
                    }
                }
        }
    }
}