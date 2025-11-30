package com.santiago.fabricio.tmdbchallenge.features.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import com.santiago.fabricio.tmdbchallenge.features.domain.repository.MoviesRepository
import com.santiago.fabricio.tmdbchallenge.features.domain.source.MoviesRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override fun getMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getMoviesPageSource()
            }
        ).flow
    }

    override fun getSearchMovies(pagingConfig: PagingConfig, query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getSearchMoviesPageSource(query)
            }
        ).flow
    }
}


