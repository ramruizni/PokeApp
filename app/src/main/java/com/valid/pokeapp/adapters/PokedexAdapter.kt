package com.valid.pokeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.valid.pokeapp.R
import com.valid.pokeapp.viewmodel.persistence.PokedexEntry
import io.reactivex.subjects.PublishSubject

class PokedexAdapter internal constructor(private val context: Context) : RecyclerView.Adapter<PokedexAdapter.PokemonViewHolder>() {

    private var layoutInflater = LayoutInflater.from(context)
    private var pokemonList = emptyList<PokedexEntry>()
    public val onClickSubject = PublishSubject.create<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.setData(pokemonList.get(position), context)
        holder.itemView.setOnClickListener{
            onClickSubject.onNext(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setPokemonList(pokemonList: List<PokedexEntry>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(entry: PokedexEntry, context: Context) {
            itemView.findViewById<TextView>(R.id.textView).text = entry.name

            Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${entry.id}.png")
                .apply(RequestOptions().placeholder(R.drawable.progress_animation))
                .into(itemView.findViewById(R.id.imageView))
        }
    }
}
