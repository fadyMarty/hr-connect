package com.hrconnect.android.domain.use_case

class ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean {
        return password.length >= 8
    }
}