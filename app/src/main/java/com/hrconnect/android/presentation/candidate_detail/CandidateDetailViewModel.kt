package com.hrconnect.android.presentation.candidate_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrconnect.android.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CandidateDetailViewModel(
    private val photoRepository: PhotoRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(CandidateDetailState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    photoBytes = photoRepository.getPhoto()
                )
            }
        }
    }

    fun onEvent(event: CandidateDetailEvent) {
        when (event) {
            is CandidateDetailEvent.OnPhotoSelected -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            photoBytes = event.bytes
                        )
                    }
                    photoRepository.savePhoto(event.bytes)
                }
            }
            CandidateDetailEvent.OnChangePhotoClick -> {
                _state.update {
                    it.copy(
                        isPhotoPickerSheetOpen = true
                    )
                }
            }
            CandidateDetailEvent.OnDismissPhotoPickerSheet -> {
                _state.update {
                    it.copy(
                        isPhotoPickerSheetOpen = false
                    )
                }
            }
        }
    }
}