package com.santiago.fabricio.tmdbchallenge.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favorite: Favorite)
    @Delete
    suspend fun delete(favorite: Favorite)
    @Query("SELECT * FROM favorities")
    fun getAllFavorities(): Flow<List<Favorite>>
}