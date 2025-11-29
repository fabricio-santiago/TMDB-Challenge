package com.santiago.fabricio.tmdbchallenge.features.data.mapper

import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import java.math.BigDecimal

fun List<Movie?>.toRepository() = map { popularMovie ->
    Movie(
        isAdult = popularMovie?.isAdult ?: false,
        backdropPath = popularMovie?.backdropPath ?: "",
        genreIds = popularMovie?.genreIds ?: listOf(),
        id = popularMovie?.id ?: 0,
        originalLanguage = popularMovie?.originalLanguage ?: "",
        originalTitle = popularMovie?.originalTitle ?: "",
        overview = popularMovie?.overview ?: "",
        popularity = popularMovie?.popularity ?: BigDecimal.ZERO,
        posterPath = popularMovie?.posterPath ?: "",
        releaseDate = popularMovie?.releaseDate ?: "",
        title = popularMovie?.title ?: "",
        isVideo = popularMovie?.isVideo ?: false,
        voteAverage = popularMovie?.voteAverage ?: 0.0f,
        voteCount = popularMovie?.voteCount ?:0
    )
}