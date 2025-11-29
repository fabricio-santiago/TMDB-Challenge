package com.santiago.fabricio.tmdbchallenge.features.data.source

import com.santiago.fabricio.tmdbchallenge.core.data.remote.paging.MoviesPageSource
import com.santiago.fabricio.tmdbchallenge.core.data.remote.response.MoviesResponse
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.MoviesService
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.tmdbchallenge.features.domain.source.MoviesRemoteDataSource
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val service: MoviesService,
    private val safeApiCaller: SafeApiCaller
) : MoviesRemoteDataSource {
    override fun getMoviesPageSource(): MoviesPageSource {
        return MoviesPageSource(this, safeApiCaller)
    }

    override suspend fun getMovies(page: Int): MoviesResponse {
        return service.getMovies(page)
    }
}