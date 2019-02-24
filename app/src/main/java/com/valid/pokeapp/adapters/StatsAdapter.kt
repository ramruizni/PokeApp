package com.valid.pokeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.valid.pokeapp.R

class StatsAdapter(val context: Context, private val items: List<String>, private val left: Boolean) : BaseAdapter() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: TextView = if (left) {
            layoutInflater.inflate(R.layout.item_stat_left, parent, false) as TextView
        } else {
            layoutInflater.inflate(R.layout.item_stat_right, parent, false) as TextView

        }
        itemView.text = items[position]
        itemView.setBackgroundColor(getBackgroundColor(position))
        return itemView
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.count()
    }

    private fun getBackgroundColor(position: Int): Int {
        return when (position) {
            0 -> ContextCompat.getColor(context, R.color.colorHp)
            1 -> ContextCompat.getColor(context, R.color.colorAttack)
            2 -> ContextCompat.getColor(context, R.color.colorDefense)
            3 -> ContextCompat.getColor(context, R.color.colorSpAtk)
            4 -> ContextCompat.getColor(context, R.color.colorSpDef)
            else -> ContextCompat.getColor(context, R.color.colorSpeed)
        }
    }

}