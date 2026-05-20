package com.hrconnect.android.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.hrconnect.android.common.util.Constants
import com.hrconnect.android.data.manager.TokenManagerImpl
import com.hrconnect.android.data.remote.AuthInterceptor
import com.hrconnect.android.data.repository.AssistantRepositoryImpl
import com.hrconnect.android.data.repository.AuthRepositoryImpl
import com.hrconnect.android.data.repository.DictionaryRepositoryImpl
import com.hrconnect.android.data.repository.EmployeeRepositoryImpl
import com.hrconnect.android.data.repository.HiringRepositoryImpl
import com.hrconnect.android.data.repository.VacancyRepositoryImpl
import com.hrconnect.android.data.validator.RegisterValidatorImpl
import com.hrconnect.android.domain.manager.TokenManager
import com.hrconnect.android.domain.repository.AssistantRepository
import com.hrconnect.android.domain.repository.AuthRepository
import com.hrconnect.android.domain.repository.DictionaryRepository
import com.hrconnect.android.domain.repository.EmployeeRepository
import com.hrconnect.android.domain.repository.HiringRepository
import com.hrconnect.android.domain.repository.VacancyRepository
import com.hrconnect.android.domain.validator.RegisterValidator
import com.hrconnect.android.presentation.assistant.AssistantViewModel
import com.hrconnect.android.presentation.loading.LoadingViewModel
import com.hrconnect.android.presentation.login.LoginViewModel
import com.hrconnect.android.presentation.register.RegisterViewModel
import com.hrconnect.android.presentation.splash.SplashViewModel
import com.hrconnect.android.presentation.vacancy_list.VacancyListViewModel
import com.hrconnect.netlib.data.remote.AuthApi
import com.hrconnect.netlib.data.remote.CandidateApi
import com.hrconnect.netlib.data.remote.DictionaryApi
import com.hrconnect.netlib.data.remote.EmployeeApi
import com.hrconnect.netlib.data.remote.HiringApi
import com.hrconnect.netlib.data.remote.VacancyApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val Context.dataStore by preferencesDataStore(name = Constants.DATASTORE_FILE_NAME)

val appModule = module {
    singleOf(::AuthInterceptor)
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }
    single {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(get<Json>().asConverterFactory(contentType))
            .client(get())
            .build()
    }
    single {
        get<Retrofit>().create(AuthApi::class.java)
    }
    single {
        get<Retrofit>().create(VacancyApi::class.java)
    }
    single {
        get<Retrofit>().create(EmployeeApi::class.java)
    }
    single {
        get<Retrofit>().create(HiringApi::class.java)
    }
    single {
        get<Retrofit>().create(DictionaryApi::class.java)
    }
    single {
        get<Retrofit>().create(CandidateApi::class.java)
    }

    singleOf(::TokenManagerImpl).bind<TokenManager>()
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::VacancyRepositoryImpl).bind<VacancyRepository>()
    singleOf(::EmployeeRepositoryImpl).bind<EmployeeRepository>()
    singleOf(::HiringRepositoryImpl).bind<HiringRepository>()
    singleOf(::DictionaryRepositoryImpl).bind<DictionaryRepository>()
    singleOf(::RegisterValidatorImpl).bind<RegisterValidator>()
    singleOf(::AssistantRepositoryImpl).bind<AssistantRepository>()

    viewModelOf(::SplashViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::LoadingViewModel)
    viewModelOf(::VacancyListViewModel)
    viewModelOf(::AssistantViewModel)
}