package com.hrconnect.android.di

import com.hrconnect.android.domain.use_case.ValidateEmailUseCase
import com.hrconnect.android.domain.use_case.ValidatePasswordUseCase
import com.hrconnect.android.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factoryOf(::ValidateEmailUseCase)
    factoryOf(::ValidatePasswordUseCase)

    viewModelOf(::RegisterViewModel)
}