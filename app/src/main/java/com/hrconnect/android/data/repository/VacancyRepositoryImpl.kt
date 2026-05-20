package com.hrconnect.android.data.repository

import com.hrconnect.android.domain.repository.VacancyRepository
import com.hrconnect.netlib.common.util.safeCall
import com.hrconnect.netlib.data.remote.VacancyApi
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class VacancyRepositoryImpl(
    private val vacancyApi: VacancyApi,
) : VacancyRepository {

    override suspend fun getVacancies(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Список вакансий"
        ) {
            vacancyApi.getVacancies()
        }
    }

    override suspend fun createVacancy(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Создание вакансии"
        ) {
            vacancyApi.createVacancy()
        }
    }

    override suspend fun getVacancyById(id: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Вакансия по ID"
        ) {
            vacancyApi.getVacancyById(id)
        }
    }

    override suspend fun updateVacancy(id: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Редактирование вакансии"
        ) {
            vacancyApi.updateVacancy(id)
        }
    }

    override suspend fun deleteVacancy(id: String): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Удаление вакансии"
        ) {
            vacancyApi.deleteVacancy(id)
        }
    }

    override suspend fun uploadVacancyFiles(
        id: String,
        files: List<File>,
    ): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Загрузка файлов вакансии"
        ) {
            vacancyApi.uploadVacancyFiles(
                id = id,
                files = files.map { file ->
                    MultipartBody.Part.createFormData(
                        name = file.nameWithoutExtension,
                        filename = file.name,
                        body = file.asRequestBody()
                    )
                }
            )
        }
    }

    override suspend fun getVacanciesShort(): Result<Unit> {
        return safeCall(
            tag = TAG,
            message = "Краткий список вакансий"
        ) {
            vacancyApi.getVacanciesShort()
        }
    }

    companion object {
        private const val TAG = "VacancyRepositoryImpl"
    }
}