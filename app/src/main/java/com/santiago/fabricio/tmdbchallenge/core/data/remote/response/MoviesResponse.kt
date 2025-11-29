package com.santiago.fabricio.tmdbchallenge.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie

data class MoviesResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
)