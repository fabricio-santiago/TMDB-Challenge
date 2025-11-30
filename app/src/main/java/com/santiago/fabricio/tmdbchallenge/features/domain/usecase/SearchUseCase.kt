package com.santiago.fabricio.tmdbchallenge.features.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import com.santiago.fabricio.tmdbchallenge.features.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface SearchUseCase {
    operator fun invoke(query: String): Flow<PagingData<Movie>>
}

class SearchUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): SearchUseCase {
    override fun invoke(query: String): Flow<PagingData<Movie>> {
        return repository.getSearchMovies(
            pagingConfig = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10
            ),
            query = query
        ).flowOn(Dispatchers.IO)
    }
}