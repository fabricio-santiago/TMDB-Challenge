package com.santiago.fabricio.tmdbchallenge.presentation

import app.cash.turbine.test
import com.santiago.fabricio.tmdbchallenge.TestDispatcherRule
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import com.santiago.fabricio.tmdbchallenge.core.data.local.repository.FavoriteRepository
import com.santiago.fabricio.tmdbchallenge.features.presentation.viewmodels.FavoritesViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var repository: FavoriteRepository
    private lateinit var viewModel: FavoritesViewModel
    private val testFavorite = Favorite(
        title = "Test Title",
        image = "Any image",
        rating = 5.0f,
        releaseDate = "2023-01-01")

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        viewModel = FavoritesViewModel(repository)
    }

    @Test
    fun `initial state is an empty list`() = runTest {
        Assert.assertEquals(emptyList<Favorite>(), viewModel.allFavorites.value)
    }

    @Test
    fun `insert calls repository's insert`() = runTest {
        viewModel.insert(testFavorite)
        coVerify(exactly = 1) { repository.insert(testFavorite) }
    }

    @Test
    fun `delete calls repository's delete`() = runTest {
        viewModel.delete(testFavorite)
        coVerify(exactly = 1) { repository.delete(testFavorite.title) }
    }

    @Test
    fun `fetchAllFavorites updates allFavorites StateFlow on success`() = runTest {
        val favoritesList = listOf(
            Favorite(title = "Favorite 1", image = "Any image 1", rating = 5.0f, releaseDate = "2023-01-01"),
            Favorite(title = "Favorite 2", image = "Any image 2", rating = 10.0f, releaseDate = "2023-02-02"),
        )
        coEvery { repository.getAll() } returns flowOf(favoritesList)

        viewModel.fetchAllFavorites()

        viewModel.allFavorites.test {
            Assert.assertEquals(emptyList<Favorite>(), awaitItem())
            Assert.assertEquals(favoritesList, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}