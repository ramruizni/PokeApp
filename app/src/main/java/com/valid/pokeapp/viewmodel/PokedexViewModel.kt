package com.valid.pokeapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.valid.pokeapp.utils.ViewModelUtils.formatName
import com.valid.pokeapp.utils.ViewModelUtils.getPokemonNumberFromUrl
import com.valid.pokeapp.viewmodel.persistence.PokedexEntry
import com.valid.pokeapp.viewmodel.persistence.PokemonDao
import com.valid.pokeapp.viewmodel.persistence.PokemonDatabase
import com.valid.pokeapp.viewmodel.repository.PokeApi
import com.valid.pokeapp.viewmodel.repository.PokeApiPokedexResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PokedexViewModel(application: Application) : AndroidViewModel(application) {

    private var pokemonDB: PokemonDatabase? = PokemonDatabase.getDatabase(getApplication())
    private var pokemonDao: PokemonDao?
    var pokemonList: LiveData<List<PokedexEntry>>? = null

    private val pokeApi by lazy {
        PokeApi.create()
    }
    private var disposable: Disposable? = null

    init {
        pokemonDao = pokemonDB!!.pokemonDao()
        pokemonList = pokemonDao!!.getPokemonList()
    }

    fun fetchPokemonList(offset: Int) {
        disposable = pokeApi.getPokemonList(151, offset*151)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onFetchSuccess, this::onFetchError)
    }

    private fun onFetchSuccess(response: PokeApiPokedexResponse) {
        //pokemonList?.value = response.results
        for (pokemon in response.results) {
            insertPokemonInDB(pokemon)
        }
    }

    private fun onFetchError(error: Throwable) {
        Log.e("error", "omgfg")
        // nothing yet
    }


    private fun insertPokemonInDB(entry: PokedexEntry) {
        Observable.fromCallable {
            with(pokemonDao) {
                this?.insert(PokedexEntry(getPokemonNumberFromUrl(entry.url), formatName(entry.name), entry.url))
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun disposeObservables() {
        disposable?.dispose()
    }

}
