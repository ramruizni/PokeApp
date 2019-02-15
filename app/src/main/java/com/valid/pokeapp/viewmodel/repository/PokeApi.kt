package com.valid.pokeapp.viewmodel.repository

import com.valid.pokeapp.viewmodel.entities.PokemonData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ):
            Observable<PokeApiPokedexResponse>

    @GET("pokemon/{id}")
    fun getPokemonData(
        @Path("id") id: Int
    ):
            Observable<PokemonData>

    companion object {
        fun create(): PokeApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://pokeapi.co/api/v2/")
                .build()

            return retrofit.create(PokeApi::class.java)
        }
    }
}
