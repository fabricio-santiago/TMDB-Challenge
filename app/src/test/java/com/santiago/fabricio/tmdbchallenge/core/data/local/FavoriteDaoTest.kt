package com.santiago.fabricio.tmdbchallenge.core.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.santiago.fabricio.tmdbchallenge.core.data.local.dao.FavoriteDao
import com.santiago.fabricio.tmdbchallenge.core.data.local.entity.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteDaoTest {

    private lateinit var database: FavoriteDatabase
    private lateinit var favoriteDao: FavoriteDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoriteDatabase::class.java
        ).allowMainThreadQueries().build()

        favoriteDao = database.favoriteDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertWord_returnsTrue() = runBlocking {
        val movie = Favorite(id = 1, title = "Mary Jane", image = "", rating = 0.0f, releaseDate = "")
        favoriteDao.insert(movie)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            favoriteDao.getAllFavorites().collect {
                assertThat(it).contains(movie)
                latch.countDown()

            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun delete_returnsTrue() = runBlocking {
        val movieOne = Favorite(id = 1, title = "Mary Jane", image = "", rating = 0.0f, releaseDate = "")
        val movieTwo = Favorite(id = 2, title = "Peter Parker", image = "", rating = 0.0f, releaseDate = "")

        favoriteDao.insert(movieOne)
        favoriteDao.insert(movieTwo)

        favoriteDao.delete(movieOne.title)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            favoriteDao.getAllFavorites().collect {
                assertThat(it).doesNotContain(movieOne.title)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()

    }
}