package com.example.avengersassemble.di.modules

import com.example.avengersassemble.data.remote.MovieApi
import com.example.avengersassemble.data.repository.MovieRepository
import com.example.avengersassemble.data.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        avengerApi: MovieApi,
        movieDao: MovieDao
    ): MovieRepository {

        return MovieRepository(movieApi = avengerApi, movieDao = movieDao)

    }

}