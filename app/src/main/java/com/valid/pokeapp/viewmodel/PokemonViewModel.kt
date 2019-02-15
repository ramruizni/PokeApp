package com.valid.pokeapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.valid.pokeapp.viewmodel.entities.PokemonData
import com.valid.pokeapp.viewmodel.persistence.PokemonDao
import com.valid.pokeapp.viewmodel.persistence.PokemonDatabase
import com.valid.pokeapp.viewmodel.repository.PokeApi
import com.valid.pokeapp.viewmodel.repository.PokeApiPokedexResponse
import com.valid.pokeapp.viewmodel.repository.PokeApiPokemonDataResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PokemonViewModel(application: Application) : AndroidViewModel(application) {

    private var pokemonDB: PokemonDatabase? = PokemonDatabase.getDatabase(getApplication())
    private var pokemonDao: PokemonDao?
    var pokemonData: MutableLiveData<PokemonData>? = null

    private val pokeApi by lazy {
        PokeApi.create()
    }
    private var disposable: Disposable? = null

    init {
        pokemonDao = pokemonDB!!.pokemonDao()
    }

    fun fetchPokemonData(id: Int) {
        disposable = pokeApi.getPokemonData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onFetchSuccess, this::onFetchError)
    }

    private fun onFetchSuccess(response: PokemonData) {
        Log.e("AZAZAOMGsuccess", response.toString())
        pokemonData?.value = response
    }

    private fun onFetchError(error: Throwable) {
        Log.e("AZAZAOMGerror", error.message)
        // nothing yet
    }

    fun disposeObservables() {
        disposable?.dispose()
    }

}
