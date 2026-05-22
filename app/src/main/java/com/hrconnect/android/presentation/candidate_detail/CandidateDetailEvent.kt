package com.hrconnect.android.presentation.candidate_detail

sealed interface CandidateDetailEvent {
    data object OnChangePhotoClick : CandidateDetailEvent
    data class OnPhotoSelected(val bytes: ByteArray) : CandidateDetailEvent
    data object OnDismissPhotoPickerSheet : CandidateDetailEvent
}