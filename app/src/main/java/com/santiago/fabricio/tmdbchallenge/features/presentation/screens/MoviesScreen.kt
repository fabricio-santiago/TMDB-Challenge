package com.santiago.fabricio.tmdbchallenge.features.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.santiago.fabricio.tmdbchallenge.R
import com.santiago.fabricio.tmdbchallenge.core.components.CustomAppBar
import com.santiago.fabricio.tmdbchallenge.features.presentation.components.MoviesContent
import com.santiago.fabricio.tmdbchallenge.features.presentation.state.MoviesState
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritesViewModel
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.MoviesViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoviesScreen() {
    val moviesViewModel: MoviesViewModel = hiltViewModel()
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()

    val uiState: MoviesState = moviesViewModel.uiState
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CustomAppBar(
                title = stringResource(id = R.string.movies_screen_title_app_bar),
            )
        },
        content = { paddingValues ->
            MoviesContent(
                pagingMovies = movies,
                paddingValues = paddingValues,
                favoritesViewModel = favoritesViewModel
            )
        }
    )
}