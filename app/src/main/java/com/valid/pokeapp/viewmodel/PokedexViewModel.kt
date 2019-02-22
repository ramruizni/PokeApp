package com.valid.pokeapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.valid.pokeapp.utils.ViewModelUtils.formatName
import com.valid.pokeapp.utils.ViewModelUtils.getPokemonNumberFromUrl
import com.valid.pokeapp.viewmodel.persistence.PokedexEntry
import com.valid.pokeapp.viewmodel.persistence.PokemonDao
import com.valid.pokeapp.viewmodel.repository.PokeApi
import com.valid.pokeapp.viewmodel.repository.PokeApiPokedexResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokedexViewModel(
    private var pokemonDao: PokemonDao,
    var pokeApi: PokeApi,
    private var disposable: CompositeDisposable
) : ViewModel() {

    var pokemonList = pokemonDao.getPokemonList()

    fun fetchPokemonList(offset: Int) {
        disposable.add(
            pokeApi.getPokemonList(151, offset * 151)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFetchSuccess, this::onFetchError)
        )
    }

    private fun onFetchSuccess(response: PokeApiPokedexResponse) {
        for (pokemon in response.results) {
            insertPokemonInDB(pokemon)
        }
    }

    private fun onFetchError(error: Throwable) {
        Log.e("error", error.message)
    }


    private fun insertPokemonInDB(entry: PokedexEntry) {
        Observable.fromCallable {
            with(pokemonDao) {
                this.insert(PokedexEntry(getPokemonNumberFromUrl(entry.url), formatName(entry.name), entry.url))
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun disposeObservables() {
        disposable.dispose()
    }
}
