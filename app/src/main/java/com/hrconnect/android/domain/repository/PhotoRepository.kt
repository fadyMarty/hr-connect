package com.hrconnect.android.domain.repository

interface PhotoRepository {
    suspend fun savePhoto(bytes: ByteArray)
    suspend fun getPhoto(): ByteArray?
}