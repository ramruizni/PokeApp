package com.valid.pokeapp.viewmodel.repository

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png
// https://pokeapi.co/api/v2/pokemon?limit=20&offset=20

interface PokeApi {

    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ):
            Observable<PokeApiResponse>

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
