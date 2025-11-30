package com.santiago.fabricio.tmdbchallenge.core.data.local.repository

import com.santiago.fabricio.tmdbchallenge.core.data.local.dao.FavoriteDao
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao: FavoriteDao
) {
    fun getAll() = dao.getAllFavorites()
    suspend fun insert(favorite: Favorite) = dao.insert(favorite)
    suspend fun delete(favorite: Favorite) = dao.delete(favorite)
}