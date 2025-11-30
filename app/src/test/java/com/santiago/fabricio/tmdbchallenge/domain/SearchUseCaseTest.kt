package com.santiago.fabricio.tmdbchallenge.domain

import androidx.paging.PagingData
import com.santiago.fabricio.tmdbchallenge.core.domain.model.MoviesFactory
import com.santiago.fabricio.tmdbchallenge.features.domain.repository.MoviesRepository
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.SearchUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchUseCaseTest {

    private lateinit var repository: MoviesRepository
    private lateinit var searchUseCase: SearchUseCaseImpl
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        searchUseCase = SearchUseCaseImpl(repository)
    }

    @Test
    fun `invoke should call repository and return search results`() = runTest(testDispatcher) {
        val query = "Inception"
        val fakePagingData = PagingData.from(listOf(
            MoviesFactory.create().results[0]
        ))
        val flowOfPagingData = flowOf(fakePagingData)

        every { repository.getSearchMovies(any(), query) } returns flowOfPagingData

        val resultFlow = searchUseCase(query)

        val resultPagingData = resultFlow.first()

        assertEquals(fakePagingData, resultPagingData)

        verify(exactly = 1) {
            repository.getSearchMovies(
                pagingConfig = withArg {
                    assertEquals(10, it.pageSize)
                    assertEquals(10, it.initialLoadSize)
                },
                query = query
            )
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}