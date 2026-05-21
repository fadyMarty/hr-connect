package com.hrconnect.android.presentation.create_vacancy

import io.github.vinceglb.filekit.PlatformFile

sealed interface CreateVacancyEvent {
    data object OnCloseClick : CreateVacancyEvent
    data class OnDepartmentSelected(val department: String) : CreateVacancyEvent
    data class OnPositionSelected(val position: String) : CreateVacancyEvent
    data class OnPublicVacancyChecked(val checked: Boolean) : CreateVacancyEvent
    data class OnDeleteFileClick(val file: PlatformFile) : CreateVacancyEvent
    data class OnFilesSelected(val files: List<PlatformFile>) : CreateVacancyEvent
}