package com.valid.pokeapp

import com.valid.pokeapp.viewmodel.PokedexViewModel
import com.valid.pokeapp.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val pokedexModule = module {
    viewModel { PokedexViewModel(get()) }
}
val pokemonModule = module {
    viewModel { PokemonViewModel(get()) }
}