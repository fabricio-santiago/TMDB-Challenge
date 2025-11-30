package com.santiago.fabricio.tmdbchallenge.core.data.remote.paging

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.santiago.fabricio.tmdbchallenge.TestDispatcherRule
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.tmdbchallenge.features.data.mapper.toRepository
import com.santiago.fabricio.tmdbchallenge.features.domain.source.LocationsRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocationsPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: LocationsRemoteDataSource

    @Mock
    lateinit var safeApiCaller: SafeApiCaller

    private val locationsPagingFactory = LocationsFactory.create()


    private val locationsPagingSource by lazy {
        LocationsPageSource(remoteDataSource = remoteDataSource, safeApiCaller = safeApiCaller)
    }

    @Test
    suspend fun `must return success load result when load is called`() {

        //Given
        whenever(remoteDataSource.getLocations(any())).thenReturn(locationsPagingFactory)

        //When
        val result = locationsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            LocationsFactory.create().results.toRepository()
        )

        //Then
        assertThat(
            PagingSource.LoadResult.Page(
                data = resultExpected,
                prevKey = null,
                nextKey = null
            )
        ).isEqualTo(
            result
        )
    }
}