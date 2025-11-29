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
import com.santiago.fabricio.tmdbchallenge.features.presentation.components.FavoritiesContent
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritiesViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritiesScreen() {

    val favoritiesViewModel: FavoritiesViewModel = hiltViewModel()
    favoritiesViewModel.fetchAllFavorites()

    val uiState = favoritiesViewModel.allFavorities.collectAsState()

    Scaffold(topBar = {
        CustomAppBar(
            title = stringResource(id = R.string.favorities_screen_title_app_bar)
        )
    },
    content = { paddingValues ->
        FavoritiesContent(
            favorities = uiState.value,
            paddingValues = paddingValues,
            favoritiesViewModel = favoritiesViewModel
        )
    })
}