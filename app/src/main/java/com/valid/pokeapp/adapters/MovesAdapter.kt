package com.valid.pokeapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.valid.pokeapp.R
import com.valid.pokeapp.utils.ViewModelUtils
import com.valid.pokeapp.viewmodel.entities.Move

class MovesAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<MovesAdapter.MovesViewHolder>() {

    private var layoutInflater = LayoutInflater.from(context)
    private var movesList = emptyList<Move>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovesViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_move, parent, false)
        return MovesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovesViewHolder, position: Int) {
        holder.setData(movesList[position], position)
    }

    override fun getItemCount(): Int {
        return movesList.size
    }

    fun setMovesList(pokemonList: List<Move>) {
        movesList = pokemonList
        notifyDataSetChanged()
    }

    class MovesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(move: Move, pos: Int) {
            itemView.findViewById<TextView>(R.id.tvLevel).text = move.versionGroupDetails[0].levelLearnedAt.toString()
            itemView.findViewById<TextView>(R.id.tvName).text = ViewModelUtils.formatName(move.move.name)
            if (pos % 2 == 0) {
                itemView.setBackgroundColor(Color.WHITE)
            } else {
                itemView.setBackgroundColor(Color.LTGRAY)
            }
        }
    }
}
