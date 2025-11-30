package com.santiago.fabricio.tmdbchallenge.features.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import com.santiago.fabricio.tmdbchallenge.R
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritesViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoritesContent(
    modifier: Modifier = Modifier,
    favorites: List<Favorite>,
    paddingValues: PaddingValues,
    favoritesViewModel: FavoritesViewModel
) {
    val context = LocalContext.current

    Surface(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxSize()
                .clearAndSetSemantics {
                    contentDescription =
                        context.getString(R.string.favorites_content_description_lazy_vertical_grid)
                }
        ) {
            items(favorites.size) { index ->
                val favorite = favorites[index]
                FavoriteItem(
                    favorite = favorite,
                    favoritesViewModel = favoritesViewModel
                )
            }
        }
    }
}