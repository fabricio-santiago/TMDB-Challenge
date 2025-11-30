package com.santiago.fabricio.tmdbchallenge.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)
    @Query("DELETE FROM favorites WHERE title = :title")
    suspend fun delete(title: String)
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<Favorite>>
}