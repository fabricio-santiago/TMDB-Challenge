package com.santiago.fabricio.tmdbchallenge.core.data.remote.service

import com.santiago.fabricio.tmdbchallenge.BuildConfig
import com.santiago.fabricio.tmdbchallenge.core.data.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesService {

    @Headers(
        "Authorization: Bearer ${BuildConfig.API_KEY}",
        "accept: application/json"
    )
    @GET("popular")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MoviesResponse
}