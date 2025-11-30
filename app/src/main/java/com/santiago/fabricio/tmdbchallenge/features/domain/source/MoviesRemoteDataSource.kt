package com.santiago.fabricio.tmdbchallenge.features.domain.source

import com.santiago.fabricio.tmdbchallenge.core.data.remote.paging.MoviesPageSource
import com.santiago.fabricio.tmdbchallenge.core.data.remote.paging.SearchPageSource
import com.santiago.fabricio.tmdbchallenge.core.data.remote.response.MoviesResponse

interface MoviesRemoteDataSource {
    fun getMoviesPageSource(): MoviesPageSource
    suspend fun getMovies(page: Int): MoviesResponse
    fun getSearchMoviesPageSource(): SearchPageSource
    suspend fun getSearchMovies(page: Int, query: String): MoviesResponse
}