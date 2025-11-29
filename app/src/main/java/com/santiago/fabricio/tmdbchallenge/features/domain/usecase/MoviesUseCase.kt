package com.santiago.fabricio.tmdbchallenge.features.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import com.santiago.fabricio.tmdbchallenge.features.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface MoviesUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}

class MoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): MoviesUseCase {
    override fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMovies(
            pagingConfig = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10
            )
        ).flowOn(Dispatchers.IO)
    }
}