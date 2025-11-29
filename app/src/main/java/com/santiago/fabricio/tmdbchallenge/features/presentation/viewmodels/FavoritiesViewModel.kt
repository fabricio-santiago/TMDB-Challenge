package com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.santiago.fabricio.tmdbchallenge.core.data.local.dao.FavoriteDao
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import com.santiago.fabricio.tmdbchallenge.core.data.local.repository.FavoriteRepository
import com.santiago.fabricio.tmdbchallenge.features.presentation.state.FavoritiesState
import com.santiago.fabricio.tmdbchallenge.features.presentation.state.MoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class FavoritiesViewModel @Inject constructor(
    private val repository: FavoriteRepository
): ViewModel() {
//    var uiState by mutableStateOf(FavoritiesState())
//        private set

    private val _allFavorities = MutableStateFlow(emptyList<Favorite>())
    val allFavorities = _allFavorities.asStateFlow()

    fun insert(favorite: Favorite) {
        viewModelScope.launch {
            repository.insert(favorite)
        }
    }

    fun delete(favorite: Favorite) {
        viewModelScope.launch {
            repository.delete(favorite)
        }
    }

    fun fetchAllUsers() {
        viewModelScope.launch { //this: CoroutineScope
            repository.getAll().flowOn(Dispatchers.IO).collect { books: List<Favorite> ->
                _allFavorities.update { books }
            }
        }
    }
}