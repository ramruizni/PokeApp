package com.valid.pokeapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.valid.pokeapp.R
import com.valid.pokeapp.adapters.MovesAdapter
import com.valid.pokeapp.viewmodel.entities.PokemonData
import kotlinx.android.synthetic.main.fragment_pokemon.*
import org.koin.android.ext.android.get

class PokemonFragment : Fragment() {

    private var movesAdapter = get<MovesAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = arguments?.getString("name")

        Glide.with(this.context!!)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${arguments?.getInt("id")}.png")
            .apply(RequestOptions().placeholder(R.drawable.progress_animation))
            .into(imageView.findViewById(R.id.imageView))

        val pokemon = arguments?.getSerializable("pokemon") as PokemonData
        val stats = pokemon.stats

        tvHp.text = stats[0].baseStat.toString()
        tvAttack.text = stats[1].baseStat.toString()
        tvDefense.text = stats[2].baseStat.toString()
        tvSpAtk.text = stats[3].baseStat.toString()
        tvSpDef.text = stats[4].baseStat.toString()
        tvSpeed.text = stats[5].baseStat.toString()

        rvAttacks.adapter = movesAdapter
        rvAttacks.layoutManager = GridLayoutManager(this.context!!, 1)

        movesAdapter.setMovesList(pokemon.moves
            .filter { it.versionGroupDetails[0].levelLearnedAt != 0 }
            .sortedBy { it.versionGroupDetails[0].levelLearnedAt })
    }
}
