package com.santiago.fabricio.tmdbchallenge.features.presentation.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.santiago.fabricio.tmdbchallenge.R
import com.santiago.fabricio.tmdbchallenge.core.data.remote.model.Movie
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritesViewModel
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchContent(
    searchViewModel: SearchViewModel,
    pagingMovies: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    favoritesViewModel: FavoritesViewModel
){
    var searchQuery by remember { mutableStateOf("") }

    val filteredItems = remember(searchQuery) {
        pagingMovies
    }

    Column(modifier = Modifier.fillMaxWidth().padding(top = 48.dp)) {
        SearchBar(
            query = searchQuery,
            onQueryChange = {  searchQuery = it },
            onSearch = { },
            active = false,
            onActiveChange = { searchViewModel.getSearchMovies(searchQuery) },
            placeholder = { Text(stringResource(id = R.string.search_bar_label)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search icon") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) { }

        LazyColumn {
            items(filteredItems.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let { item ->
                    MovieItem(
                        movie = item,
                        favoritesViewModel = favoritesViewModel
                    )
                }
            }
        }
    }
}