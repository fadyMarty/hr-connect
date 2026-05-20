package com.hrconnect.android.data.repository

import com.hrconnect.android.data.mappers.toAuthResponse
import com.hrconnect.android.data.mappers.toUser
import com.hrconnect.android.domain.manager.TokenManager
import com.hrconnect.android.domain.model.AuthResponse
import com.hrconnect.android.domain.model.User
import com.hrconnect.android.domain.repository.AuthRepository
import com.hrconnect.netlib.common.util.safeCall
import com.hrconnect.netlib.data.remote.AuthApi
import com.hrconnect.netlib.data.remote.dto.LoginRequestDto

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager,
) : AuthRepository {

    override suspend fun login(
        username: String,
        password: String,
        rememberUser: Boolean,
    ): Result<AuthResponse> {
        return safeCall(
            tag = TAG,
            message = "Авторизация"
        ) {
            val authResponse = authApi.login(
                request = LoginRequestDto(
                    username = username,
                    password = password
                )
            ).toAuthResponse()
            if (rememberUser) {
                tokenManager.saveToken(authResponse.accessToken)
            }
            authResponse
        }
    }

    override suspend fun register(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Регистрация"
        ) {
            authApi.register()
            tokenManager.saveToken("")
        }
    }

    override suspend fun getProfileByToken(): Result<User> {
        return safeCall(
            tag = TAG,
            message = "Получение профиля по токену"
        ) {
            authApi.getProfileByToken().toUser()
        }
    }

    override suspend fun logout(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Выход"
        ) {
            authApi.logout()
            tokenManager.deleteToken()
        }
    }

    override suspend fun getAvatarById(id: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Аватар пользователя"
        ) {
            authApi.getAvatarById(id)
        }
    }

    companion object {
        private const val TAG = "AuthRepositoryImpl"
    }
}