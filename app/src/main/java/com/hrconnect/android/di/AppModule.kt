package com.hrconnect.android.di

import com.hrconnect.android.data.repository.VacancyRepositoryImpl
import com.hrconnect.android.domain.repository.VacancyRepository
import com.hrconnect.android.domain.use_case.ValidateEmailUseCase
import com.hrconnect.android.domain.use_case.ValidatePasswordUseCase
import com.hrconnect.android.presentation.loading.LoadingViewModel
import com.hrconnect.android.presentation.login.LoginViewModel
import com.hrconnect.android.presentation.register.RegisterViewModel
import com.hrconnect.android.presentation.vacancy_list.VacancyListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::VacancyRepositoryImpl).bind<VacancyRepository>()

    factoryOf(::ValidateEmailUseCase)
    factoryOf(::ValidatePasswordUseCase)

    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::LoadingViewModel)
    viewModelOf(::VacancyListViewModel)
}