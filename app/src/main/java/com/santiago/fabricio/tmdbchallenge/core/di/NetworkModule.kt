package com.santiago.fabricio.tmdbchallenge.core.di

import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.MoviesService
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.ParamsInterceptor
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.SafeApiCaller
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideSafeApiCaller(backgroundDispatcher: CoroutineDispatcher): SafeApiCaller {
        return SafeApiCaller(backgroundDispatcher)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @Provides
    fun provideParamsInterceptor(): ParamsInterceptor {
        return ParamsInterceptor()
    }

    @Provides
    fun provideOkHttpClient(
        paramsInterceptor: ParamsInterceptor, loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(paramsInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideMoviesService(
        client: OkHttpClient, converterFactory: GsonConverterFactory
    ): MoviesService {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").client(client)
            .addConverterFactory(converterFactory).build().create(MoviesService::class.java)
    }
}