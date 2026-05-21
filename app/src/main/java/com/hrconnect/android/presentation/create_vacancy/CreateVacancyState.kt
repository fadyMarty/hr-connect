package com.hrconnect.android.presentation.create_vacancy

import androidx.compose.foundation.text.input.TextFieldState
import io.github.vinceglb.filekit.PlatformFile

data class CreateVacancyState(
    val vacancyTitleState: TextFieldState = TextFieldState(),
    val department: String = "Product & Design",
    val openingDateState: TextFieldState = TextFieldState(),
    val position: String = "Full-time",
    val salaryRangeState: TextFieldState = TextFieldState(),
    val requiredQuantityState: TextFieldState = TextFieldState("1"),
    val experienceState: TextFieldState = TextFieldState(),
    val cityState: TextFieldState = TextFieldState("New York, NY"),
    val publicVacancy: Boolean = true,
    val descriptionState: TextFieldState = TextFieldState(),
    val termsOfEmploymentState: TextFieldState = TextFieldState(),
    val requirementsState: TextFieldState = TextFieldState(),
    val technicalQuestionsState: TextFieldState = TextFieldState(),
    val testTaskState: TextFieldState = TextFieldState(),
    val supportDocuments: List<PlatformFile> = emptyList(),
    val hrManager: String? = null,
    val phoneState: TextFieldState = TextFieldState(),
    val emailState: TextFieldState = TextFieldState(),
    val responsiblePerson: String? = null,
)
