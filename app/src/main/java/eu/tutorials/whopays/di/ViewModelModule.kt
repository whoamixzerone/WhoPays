package eu.tutorials.whopays.di

import eu.tutorials.whopays.presentation.screen.round.RoundViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModelModule = module {
    viewModelOf(::RoundViewModel)
}