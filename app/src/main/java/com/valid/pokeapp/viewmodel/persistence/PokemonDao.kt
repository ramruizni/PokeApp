package com.valid.pokeapp.viewmodel.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pokedexEntry: PokedexEntry)

    @Query("SELECT * FROM pokedexEntry")
    fun getPokemonList(): LiveData<List<PokedexEntry>>
}
