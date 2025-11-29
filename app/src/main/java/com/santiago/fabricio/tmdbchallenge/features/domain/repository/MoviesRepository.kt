package com.santiago.fabricio.tmdbchallenge.features.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}