package com.kumar.test.di

import com.kumar.test.data.repository.PhotoRepository
import org.koin.dsl.module


val restRepositoryModule = module {
    single {
        PhotoRepository(get())
    }
}