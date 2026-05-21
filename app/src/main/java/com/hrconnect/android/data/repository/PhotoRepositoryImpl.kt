package com.hrconnect.android.data.repository

import com.hrconnect.android.domain.repository.PhotoRepository
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.exists
import io.github.vinceglb.filekit.filesDir
import io.github.vinceglb.filekit.readBytes
import io.github.vinceglb.filekit.write

class PhotoRepositoryImpl : PhotoRepository {

    override suspend fun savePhoto(bytes: ByteArray) {
        val file = PlatformFile(FileKit.filesDir, PHOTO_FILE_NAME)
        file.write(bytes)
    }

    override suspend fun getPhoto(): ByteArray? {
        val file = PlatformFile(FileKit.filesDir, PHOTO_FILE_NAME)
        return if (file.exists()) {
            file.readBytes()
        } else null
    }

    companion object {
        private const val PHOTO_FILE_NAME = "photo.jpg"
    }
}