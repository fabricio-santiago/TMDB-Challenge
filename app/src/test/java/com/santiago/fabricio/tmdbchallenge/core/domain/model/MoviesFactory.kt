package com.santiago.fabricio.tmdbchallenge.core.domain.model

import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import com.santiago.fabricio.tmdbchallenge.core.data.remote.response.MoviesResponse
import java.math.BigDecimal

class MoviesFactory {

    companion object {
        fun create() = run {
            MoviesResponse(
                page = 1,
                results = listOf(
                    Movie(
                        isAdult = false,
                        backdropPath = "",
                        genreIds = listOf(),
                        id = 361,
                        originalLanguage = "pt-BR",
                        originalTitle = "Titulo Original",
                        overview = "",
                        popularity = BigDecimal.valueOf(7.80),
                        posterPath = "",
                        releaseDate = "2025-11-30",
                        title = "Título",
                        isVideo = true,
                        voteAverage = 6.9f,
                        voteCount = 100,
                    )
                )
            )
        }
    }
}