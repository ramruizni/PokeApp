package com.valid.pokeapp

import android.app.Application
import org.koin.android.ext.android.startKoin

class KoinApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(pokedexModule, pokemonModule))
    }

}