package com.it.taskforattractor.di

import com.it.taskforattractor.ui.gallery.GalleryViewModel
import com.it.taskforattractor.ui.greeting.GreetingsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { GreetingsViewModel(get()) }
    viewModel { GalleryViewModel(get()) }
}