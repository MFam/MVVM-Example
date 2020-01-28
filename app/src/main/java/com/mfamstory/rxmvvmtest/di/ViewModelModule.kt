package com.mfamstory.rxmvvmtest.di

import com.mfamstory.rxmvvmtest.ui.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}