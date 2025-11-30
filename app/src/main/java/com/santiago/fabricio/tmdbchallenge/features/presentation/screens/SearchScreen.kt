package com.santiago.fabricio.tmdbchallenge.features.presentation.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.santiago.fabricio.tmdbchallenge.R
import com.santiago.fabricio.tmdbchallenge.core.components.CustomAppBar
import com.santiago.fabricio.tmdbchallenge.features.presentation.components.SearchContent
import com.santiago.fabricio.tmdbchallenge.features.presentation.state.MoviesState
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritesViewModel
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.SearchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen() {

    val searchViewModel: SearchViewModel = hiltViewModel()
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()

    val uiState: MoviesState = searchViewModel.uiState
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(topBar = {
        CustomAppBar(
            title = stringResource(id = R.string.search_screen_title_app_bar)
        )
    },
    content = { paddingValues ->
        SearchContent(
            searchViewModel = searchViewModel,
            pagingMovies = movies,
            paddingValues = paddingValues,
            favoritesViewModel = favoritesViewModel
        )
    })
}