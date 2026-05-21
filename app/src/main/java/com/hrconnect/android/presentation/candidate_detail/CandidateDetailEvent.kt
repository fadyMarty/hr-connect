package com.hrconnect.android.presentation.candidate_detail

sealed interface CandidateDetailEvent {
    data class OnPhotoPicked(val bytes: ByteArray) : CandidateDetailEvent
    data object OnChangePhotoClick : CandidateDetailEvent
    data object OnDismissPhotoPickerSheet : CandidateDetailEvent
}