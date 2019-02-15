package com.valid.pokeapp.viewmodel.repository

import com.valid.pokeapp.viewmodel.persistence.PokedexEntry

class PokeApiPokedexResponse {
    lateinit var results: List<PokedexEntry>
}
