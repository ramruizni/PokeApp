package com.valid.pokeapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valid.pokeapp.R
import com.valid.pokeapp.adapters.PokedexAdapter
import com.valid.pokeapp.viewmodel.PokedexViewModel
import com.valid.pokeapp.viewmodel.PokemonViewModel
import com.valid.pokeapp.viewmodel.persistence.PokedexEntry
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pokedex.*

class PokedexFragment : Fragment() {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var pokedexViewModel: PokedexViewModel
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var pokedexEntrySelected: PokedexEntry
    private var disposable = CompositeDisposable()

    var canReload = true
    var offset = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = "PokeApp"

        pokedexViewModel = ViewModelProviders.of(activity!!).get(PokedexViewModel::class.java)
        pokemonViewModel = ViewModelProviders.of(activity!!).get(PokemonViewModel::class.java)

        pokedexAdapter = PokedexAdapter(this.context!!)
        recyclerView.adapter = pokedexAdapter
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this.context!!, 3)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val pastItems = layoutManager.findFirstVisibleItemPosition()
                    if (canReload && (layoutManager.childCount + pastItems) >= layoutManager.itemCount) {
                        canReload = false
                        pokedexViewModel.fetchPokemonList(++offset)
                    }
                }
            }
        })

        pokedexViewModel.pokemonList.observe(this, Observer {
            pokedexAdapter.setPokemonList(it)
            canReload = true
        })
        pokedexViewModel.fetchPokemonList(offset)

        disposable.add(pokedexAdapter.onClickSubject.subscribe {
            activity?.progressBar?.visibility = View.VISIBLE
            pokemonViewModel.fetchPokemonData(it.id!!)
            pokedexEntrySelected = it
        })

        disposable.add(pokemonViewModel.pokemonData.subscribe {
            val bundle = Bundle()
            bundle.putInt("id", pokedexEntrySelected.id!!)
            bundle.putString("name", pokedexEntrySelected.name)
            bundle.putSerializable("pokemon", it)

            activity?.progressBar?.visibility = View.GONE

            if (findNavController().currentDestination?.id == R.id.pokedexFragment) {
                findNavController().navigate(R.id.toPokemon, bundle)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        pokedexViewModel.disposeObservables()
        pokemonViewModel.disposeObservables()
        disposable.dispose()
    }
}
