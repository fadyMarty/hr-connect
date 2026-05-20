package com.hrconnect.android.data.mappers

import com.hrconnect.android.domain.model.User
import com.hrconnect.netlib.data.remote.dto.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        image = image,
        role = role
    )
}