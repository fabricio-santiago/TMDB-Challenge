package com.santiago.fabricio.tmdbchallenge.presentation

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.whenever
import com.santiago.fabricio.tmdbchallenge.TestDispatcherRule
import com.santiago.fabricio.tmdbchallenge.features.data.mapper.toRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.santiago.fabricio.tmdbchallenge.core.domain.model.CharactersFactory
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.CharactersUseCase
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.MoviesViewModel
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var charactersUseCase: CharactersUseCase

    private val viewModel by lazy {
        MoviesViewModel(charactersUseCase = charactersUseCase)
    }

    private val fakePagingDataCharacters = PagingData.from(
        CharactersFactory.create().results.toRepository()
    )

    @Test
    fun `must validate paging data object values when calling paging data from characters`() =
        runTest {
            //Given
            whenever(charactersUseCase.invoke()).thenReturn(
                flowOf(fakePagingDataCharacters)
            )

            //When
            val result = viewModel.uiState.characters.first()

            //Then
            assertThat(result).isNotNull()
        }

    @Test
    fun `must thrown an exception when the calling to the use case return an exception`() =
        runTest {
            //Given
            whenever(charactersUseCase.invoke()).thenThrow(RuntimeException())

            //When
            val result = viewModel.uiState.characters

            //Then
            assertThat(result).isNotSameInstanceAs(fakePagingDataCharacters)
        }
}