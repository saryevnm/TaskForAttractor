package com.it.taskforattractor.di

import com.it.taskforattractor.ui.gallery.GalleryRepository
import com.it.taskforattractor.ui.gallery.GalleryRepositoryImpl
import com.it.taskforattractor.ui.greeting.GreetingsRepository
import com.it.taskforattractor.ui.greeting.GreetingsRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single<GreetingsRepository> { GreetingsRepositoryImpl(get()) }
    single<GalleryRepository> { GalleryRepositoryImpl(get()) }
}