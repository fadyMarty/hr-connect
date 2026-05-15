package com.hrconnect.android.domain.use_case

class ValidateEmailUseCase {
    operator fun invoke(email: String): Boolean {
        return EMAIL_PATTERN.toRegex().matches(email)
    }

    companion object {
        private const val EMAIL_PATTERN = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]+$"
    }
}