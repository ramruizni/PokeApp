package com.valid.pokeapp.viewmodel.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey(autoGenerate = false) var id: Int?,
    val name: String,
    val url: String
)
