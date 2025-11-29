package com.santiago.fabricio.tmdbchallenge.core.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.NavigationItem
import com.santiago.fabricio.tmdbchallenge.core.util.Constants.FAVORITES_SCREEN_ROUTE
import com.santiago.fabricio.tmdbchallenge.core.util.Constants.MOVIES_SCREEN_ROUTE
import com.santiago.fabricio.tmdbchallenge.core.util.Constants.SEARCH_SCREEN_ROUTE

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem(
            title = "Filmes",
            icon = Icons.Default.Movie,
            route = Screen.Movies.rout
        ),
        NavigationItem(
            title = "Favoritos",
            icon = Icons.Default.Star,
            route = Screen.Favorites.rout
        ),
        NavigationItem(
            title = "Pesquisa",
            icon = Icons.Default.Search,
            route = Screen.Search.rout
        )
    )

    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex.intValue)
                            Color.Black
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
}

sealed class Screen(val rout: String) {
    data object Movies: Screen(MOVIES_SCREEN_ROUTE)
    data object Favorites: Screen(FAVORITES_SCREEN_ROUTE)
    data object Search: Screen(SEARCH_SCREEN_ROUTE)
}