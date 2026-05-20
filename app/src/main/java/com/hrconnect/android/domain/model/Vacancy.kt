package com.hrconnect.android.domain.model

import java.util.UUID

data class Vacancy(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val department: String,
    val employment: String,
    val minSalary: Int,
    val maxSalary: Int,
    val applicantsCount: Int,
    val isActive: Boolean,
)
