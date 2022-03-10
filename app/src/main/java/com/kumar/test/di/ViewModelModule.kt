package com.kumar.test.di

import com.kumar.test.presentation.viewmodel.PictureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PictureViewModel() }
}