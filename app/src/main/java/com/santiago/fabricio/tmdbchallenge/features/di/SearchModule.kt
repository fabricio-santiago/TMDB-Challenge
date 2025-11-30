package com.santiago.fabricio.tmdbchallenge.features.di

import com.santiago.fabricio.tmdbchallenge.features.domain.repository.MoviesRepository
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.SearchUseCase
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.SearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {
    @Provides
    @Singleton
    fun provideSearchUseCase(repository: MoviesRepository): SearchUseCase {
        return SearchUseCaseImpl(repository = repository)
    }
}