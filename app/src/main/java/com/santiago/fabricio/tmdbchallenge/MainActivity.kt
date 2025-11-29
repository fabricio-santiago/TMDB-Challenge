package com.santiago.fabricio.tmdbchallenge

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.santiago.fabricio.tmdbchallenge.core.data.local.FavoriteDatabase
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritiesViewModel
import com.santiago.fabricio.tmdbchallenge.ui.theme.InterviewTechnicalTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val favoriteDao = FavoriteDatabase.getDatabase(this).favoriteDao()

//        val favoritesViewModel = FavoritiesViewModel(favoriteDao)
//        favoritesViewModel.fetchAllUsers()

       // val a = favoritesViewModel.allFavorities.value

        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            InterviewTechnicalTestTheme {
                MainScreen(navController = rememberNavController(), favoriteDao)
            }
        }
    }
}
