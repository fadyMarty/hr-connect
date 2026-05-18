package com.hrconnect.android.di

import com.hrconnect.android.data.repository.AssistantRepositoryImpl
import com.hrconnect.android.data.repository.VacancyRepositoryImpl
import com.hrconnect.android.domain.repository.AssistantRepository
import com.hrconnect.android.domain.repository.VacancyRepository
import com.hrconnect.android.presentation.assistant.AssistantViewModel
import com.hrconnect.android.presentation.loading.LoadingViewModel
import com.hrconnect.android.presentation.login.LoginViewModel
import com.hrconnect.android.presentation.register.RegisterViewModel
import com.hrconnect.android.presentation.splash.SplashViewModel
import com.hrconnect.android.presentation.vacancy_list.VacancyListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::VacancyRepositoryImpl).bind<VacancyRepository>()
    singleOf(::AssistantRepositoryImpl).bind<AssistantRepository>()

    viewModelOf(::SplashViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::LoadingViewModel)
    viewModelOf(::VacancyListViewModel)
    viewModelOf(::AssistantViewModel)
}