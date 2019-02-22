package com.valid.pokeapp

import com.valid.pokeapp.adapters.MovesAdapter
import com.valid.pokeapp.adapters.PokedexAdapter
import com.valid.pokeapp.viewmodel.PokedexViewModel
import com.valid.pokeapp.viewmodel.PokemonViewModel
import com.valid.pokeapp.viewmodel.entities.PokemonData
import com.valid.pokeapp.viewmodel.persistence.PokemonDao
import com.valid.pokeapp.viewmodel.persistence.PokemonDatabase
import com.valid.pokeapp.viewmodel.repository.PokeApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val commonsModule = module {
    single { PokemonDatabase.getDatabase(get())!!.pokemonDao()}
    single { PokeApi.create() }
    factory { CompositeDisposable() }
}

val pokedexModule = module {
    viewModel { PokedexViewModel(
        get() as PokemonDao,
        get() as PokeApi,
        get() as CompositeDisposable
    )}
}

val pokemonModule = module {
    viewModel { PokemonViewModel(
        PublishSubject.create<PokemonData>(),
        get() as PokeApi,
        get() as CompositeDisposable
    )}
}

val adaptersModule = module {
    single { PokedexAdapter(get())}
    single { MovesAdapter(get()) }
}
