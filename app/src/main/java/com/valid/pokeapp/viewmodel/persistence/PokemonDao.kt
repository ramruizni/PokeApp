package com.valid.pokeapp.viewmodel.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): LiveData<List<Pokemon>>
}
