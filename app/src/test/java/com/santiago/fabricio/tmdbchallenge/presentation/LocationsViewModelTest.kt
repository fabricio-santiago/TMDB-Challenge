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
import com.santiago.fabricio.tmdbchallenge.core.domain.model.LocationsFactory
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.LocationsUseCase
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LocationsViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var locationsUseCase: LocationsUseCase

    private val viewModel by lazy {
        LocationsViewModel(locationsUseCase = locationsUseCase)
    }

    private val fakePagingDataLocations = PagingData.from(
        LocationsFactory.create().results.toRepository()
    )

    @Test
    fun `must validate paging data object values when calling paging data from locations`() =
        runTest {
            //Given
            whenever(locationsUseCase.invoke()).thenReturn(
                flowOf(fakePagingDataLocations)
            )

            //When
            val result = viewModel.uiState.locations.first()

            //Then
            assertThat(result).isNotNull()
        }

    @Test
    fun `must thrown an exception when the calling to the use case return an exception`() =
        runTest {
            //Given
            whenever(locationsUseCase.invoke()).thenThrow(RuntimeException())

            //When
            val result = viewModel.uiState.locations

            //Then
            assertThat(result).isNotSameInstanceAs(fakePagingDataLocations)
        }
}