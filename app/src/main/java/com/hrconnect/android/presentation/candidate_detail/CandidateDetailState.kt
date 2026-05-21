package com.hrconnect.android.presentation.candidate_detail

data class CandidateDetailState(
    val photoBytes: ByteArray? = null,
    val isPhotoPickerSheetOpen: Boolean = false,
)
