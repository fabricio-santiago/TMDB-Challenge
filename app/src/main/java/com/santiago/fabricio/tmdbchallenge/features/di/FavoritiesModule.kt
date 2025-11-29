package com.santiago.fabricio.tmdbchallenge.features.di

import android.content.Context
import androidx.room.Room
import com.santiago.fabricio.tmdbchallenge.core.data.local.FavoriteDatabase
import com.santiago.fabricio.tmdbchallenge.core.data.local.dao.FavoriteDao
import com.santiago.fabricio.tmdbchallenge.core.data.local.repository.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritiesModule {

    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteDao: FavoriteDao): FavoriteRepository {
        return FavoriteRepository(dao = favoriteDao)
    }

    @Provides
    @Singleton
    fun provideUserDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            "favorite_database"
        ).build()
    }
}