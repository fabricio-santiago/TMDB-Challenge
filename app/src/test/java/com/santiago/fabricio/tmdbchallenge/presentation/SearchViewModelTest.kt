package com.santiago.fabricio.tmdbchallenge.presentation

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import com.santiago.fabricio.tmdbchallenge.TestDispatcherRule
import com.santiago.fabricio.tmdbchallenge.core.domain.model.MoviesFactory
import com.santiago.fabricio.tmdbchallenge.features.data.mapper.toRepository
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.SearchUseCase
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var searchUseCase: SearchUseCase

    private val viewModel by lazy {
        SearchViewModel(searchUseCase = searchUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        MoviesFactory.create().results.toRepository()
    )

    @Test
    fun `must validate paging data object values when calling paging data from movies`() =
        runTest {
            //Given
            whenever(searchUseCase.invoke("voo")).thenReturn(
                flowOf(fakePagingDataMovies)
            )

            //When
            val result = viewModel.uiState.movies.first()

            //Then
            assertThat(result).isNotNull()
        }

    @Test
    fun `must thrown an exception when the calling to the use case return an exception`() =
        runTest {
            //Given
            whenever(searchUseCase.invoke("")).thenThrow(RuntimeException())

            //When
            val result = viewModel.uiState.movies

            //Then
            assertThat(result).isNotSameInstanceAs(fakePagingDataMovies)
        }
}