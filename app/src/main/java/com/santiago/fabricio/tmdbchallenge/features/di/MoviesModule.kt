package com.santiago.fabricio.tmdbchallenge.features.di

import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.MoviesService
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.SafeApiCaller
import com.santiago.fabricio.tmdbchallenge.features.data.repository.MoviesRepositoryImpl
import com.santiago.fabricio.tmdbchallenge.features.data.source.MoviesRemoteDataSourceImpl
import com.santiago.fabricio.tmdbchallenge.features.domain.repository.MoviesRepository
import com.santiago.fabricio.tmdbchallenge.features.domain.source.MoviesRemoteDataSource
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.MoviesUseCase
import com.santiago.fabricio.tmdbchallenge.features.domain.usecase.MoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Provides
    @Singleton
    fun provideMoviesRemoteDataSource(
        service: MoviesService,
        safeApiCaller: SafeApiCaller
    ): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(service = service, safeApiCaller = safeApiCaller)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(remoteDataSource: MoviesRemoteDataSource): MoviesRepository {
        return MoviesRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideMoviesUseCase(repository: MoviesRepository): MoviesUseCase {
        return MoviesUseCaseImpl(repository = repository)
    }
}