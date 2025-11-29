package com.santiago.fabricio.tmdbchallenge.features.presentation.state

import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FavoritiesState (
    var favorities: List<Favorite> = listOf()
)