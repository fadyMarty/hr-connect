package com.hrconnect.android.data.mappers

import com.hrconnect.android.domain.model.AuthResponse
import com.hrconnect.netlib.data.remote.dto.AuthResponseDto

fun AuthResponseDto.toAuthResponse(): AuthResponse {
    return AuthResponse(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        image = image,
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}