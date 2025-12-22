package eu.tutorials.whopays.di

import eu.tutorials.whopays.presentation.screen.round.RoundViewModel
import eu.tutorials.whopays.presentation.screen.slotmachine.SlotMachineViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModelModule = module {
    viewModelOf(::RoundViewModel)

    viewModel { (round: Int) ->
        SlotMachineViewModel(round = round)
    }
}