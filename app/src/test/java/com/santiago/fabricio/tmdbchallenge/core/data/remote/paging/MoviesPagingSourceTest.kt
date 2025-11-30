package com.santiago.fabricio.tmdbchallenge.core.data.remote.paging

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.santiago.fabricio.tmdbchallenge.TestDispatcherRule
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.tmdbchallenge.core.domain.model.MoviesFactory
import com.santiago.fabricio.tmdbchallenge.features.data.mapper.toRepository
import com.santiago.fabricio.tmdbchallenge.features.domain.source.MoviesRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MoviesRemoteDataSource

    @Mock
    lateinit var safeApiCaller: SafeApiCaller

    private val moviesPagingFactory = MoviesFactory.create()

    private val moviesPagingSource by lazy {
        MoviesPageSource(remoteDataSource = remoteDataSource, safeApiCaller = safeApiCaller)
    }

    @Test
    suspend fun `must return success load result when load is called`() {

        //Given
        whenever(remoteDataSource.getMovies(any())).thenReturn(moviesPagingFactory)

        //When
        val result = moviesPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            MoviesFactory.create().results.toRepository()
        )

        //Then
        assertThat(
            PagingSource.LoadResult.Page(
                data = resultExpected,
                prevKey = null,
                nextKey = null
            )
        ).isEqualTo(result)
    }
}