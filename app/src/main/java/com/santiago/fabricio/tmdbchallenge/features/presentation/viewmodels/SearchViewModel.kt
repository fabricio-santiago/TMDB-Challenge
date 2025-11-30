package com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.SearchUseCase
import com.santiago.fabricio.tmdbchallenge.features.presentation.state.MoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    var uiState by mutableStateOf(MoviesState())
        private set

    fun getSearchMovies(query: String) {
        try {
            val movies = searchUseCase.invoke(query).cachedIn(viewModelScope)
            uiState = uiState.copy(movies = movies)
        } catch (e: Exception){
            uiState = uiState.copy(movies = flowOf())
        }
    }
}