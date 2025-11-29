package com.santiago.fabricio.tmdbchallenge

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.santiago.fabricio.tmdbchallenge.core.components.BottomNavigationBar
import com.santiago.fabricio.tmdbchallenge.core.components.Screen
import com.santiago.fabricio.tmdbchallenge.core.data.local.dao.FavoriteDao
import com.santiago.fabricio.tmdbchallenge.features.presentation.screens.FavoritiesScreen
import com.santiago.fabricio.tmdbchallenge.features.presentation.screens.MoviesScreen

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    favoriteDao: FavoriteDao
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->

        val graph =
            navController.createGraph(startDestination = Screen.Movies.rout) {
                composable(route = Screen.Movies.rout) {
                    MoviesScreen(favoriteDao)
                }
                composable(route = Screen.Favorites.rout) {
                    FavoritiesScreen(favoriteDao)
                }
                composable(route = Screen.Search.rout) {
                    MoviesScreen(favoriteDao)
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )

    }
}
