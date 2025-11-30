package com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import com.santiago.fabricio.tmdbchallenge.core.data.local.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoriteRepository
): ViewModel() {
    private val _allFavorites = MutableStateFlow(emptyList<Favorite>())
    val allFavorites = _allFavorites.asStateFlow()

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

    fun fetchAllFavorites() {
        viewModelScope.launch {
            repository.getAll().flowOn(Dispatchers.IO).collect { favorites: List<Favorite> ->
                _allFavorites.update { favorites }
            }
        }
    }
}