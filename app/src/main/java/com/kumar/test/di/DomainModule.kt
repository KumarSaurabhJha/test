package com.kumar.test.di

import com.kumar.test.domain.usecase.GetPictureUseCase
import com.kumar.test.domain.usecase.GetPicturesListUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetPicturesListUseCase(get()) }
    single { GetPictureUseCase(get()) }
}