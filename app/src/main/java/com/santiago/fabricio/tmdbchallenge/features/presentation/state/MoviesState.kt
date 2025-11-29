package com.santiago.fabricio.tmdbchallenge.features.presentation.state

import androidx.paging.PagingData
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviesState(
    var movies: Flow<PagingData<Movie>> = emptyFlow()
)

