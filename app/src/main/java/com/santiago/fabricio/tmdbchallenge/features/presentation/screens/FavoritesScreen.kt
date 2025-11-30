package com.santiago.fabricio.tmdbchallenge.features.presentation.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.santiago.fabricio.tmdbchallenge.R
import com.santiago.fabricio.tmdbchallenge.core.components.CustomAppBar
import com.santiago.fabricio.tmdbchallenge.features.presentation.components.FavoritesContent
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritesViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesScreen() {

    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
    //favoritesViewModel.fetchAllFavorites()

    val uiState = favoritesViewModel.allFavorites.collectAsState()

    Scaffold(topBar = {
        CustomAppBar(
            title = stringResource(id = R.string.favorites_screen_title_app_bar)
        )
    },
    content = { paddingValues ->
        FavoritesContent(
            favorites = uiState.value,
            paddingValues = paddingValues,
            favoritesViewModel = favoritesViewModel
        )
    })
}