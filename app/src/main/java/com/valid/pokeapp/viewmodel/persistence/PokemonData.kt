package com.valid.pokeapp.viewmodel.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonData(
    @PrimaryKey(autoGenerate = false) var id: Int,
    val name: String,
    val stats: ArrayList<String>,
    val attacks: HashMap<Int, String>
)
