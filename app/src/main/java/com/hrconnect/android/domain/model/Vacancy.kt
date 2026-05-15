package com.hrconnect.android.domain.model

data class Vacancy(
    val title: String,
    val company: String,
    val employment: String,
    val minSalary: Int,
    val maxSalary: Int,
    val applicantsCount: Int,
    val isActive: Boolean,
)
