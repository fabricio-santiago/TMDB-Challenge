package com.santiago.fabricio.tmdbchallenge.core.data.remote.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
