package com.valid.pokeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valid.pokeapp.R
import com.valid.pokeapp.viewmodel.persistence.Pokemon

class PokedexAdapter internal constructor(context: Context) : RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder>() {

    private var layoutInflater = LayoutInflater.from(context)
    private var pokemonList = emptyList<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.setData(pokemonList.get(position).name)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setPokemonList(pokemonList: List<Pokemon>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(note: String) {
            itemView.findViewById<TextView>(R.id.textView).text = note
        }
    }
}
