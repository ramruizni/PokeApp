package com.valid.pokeapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.valid.pokeapp.viewmodel.entities.PokemonData
import com.valid.pokeapp.viewmodel.repository.PokeApi
import io.reactivex.Notification
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class PokemonViewModel(
    var pokemonData: PublishSubject<PokemonData>,
    var pokeApi: PokeApi,
    private var disposable: CompositeDisposable
) : ViewModel() {

    fun fetchPokemonData(id: Int) {
        disposable.add(
            pokeApi.getPokemonData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFetchSuccess, this::onFetchError)
        )
    }

    private fun onFetchSuccess(response: PokemonData) {
        pokemonData.onNext(response)
    }

    private fun onFetchError(error: Throwable) {
        Log.e("AZAZAOMGerror", error.message)
        pokemonData.onError(error)
    }

    fun disposeObservables() {
        disposable.dispose()
    }

}
