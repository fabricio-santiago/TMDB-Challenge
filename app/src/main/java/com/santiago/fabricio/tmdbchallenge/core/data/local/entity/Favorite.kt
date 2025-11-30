package com.santiago.fabricio.tmdbchallenge.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val image: String,
    val rating: Float,
    val releaseDate: String
)
