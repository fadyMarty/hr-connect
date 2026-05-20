package com.hrconnect.android.domain.model

data class AuthResponse(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String,
)