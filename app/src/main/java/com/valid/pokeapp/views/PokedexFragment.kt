package com.valid.pokeapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.valid.pokeapp.R
import com.valid.pokeapp.adapters.PokedexAdapter
import com.valid.pokeapp.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_pokedex.*

class PokedexFragment : Fragment() {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(PokemonViewModel::class.java)

        pokedexAdapter = PokedexAdapter(this.context!!)
        recyclerView.adapter = pokedexAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context!!, 3)

        viewModel.fetchPokemonList()
        viewModel.pokemonList?.observe(this, Observer {
            pokedexAdapter.setPokemonList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeObservables()
    }

}
