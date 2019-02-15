package com.valid.pokeapp.viewmodel.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokedexEntry::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        var instance: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase? {
            if (instance == null) {
                synchronized(PokemonDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDatabase::class.java,
                        "pokemon_database"
                    ).build()
                }
            }
            return instance
        }
    }

}
