package com.example.avengersassemble.di.modules

import android.content.Context
import androidx.room.Room
import com.example.avengersassemble.util.Constants.DATABASE_NAME
import com.example.avengersassemble.data.local.dao.MovieDao
import com.example.avengersassemble.data.local.dataBase.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideHackerNewsDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {

        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            DATABASE_NAME
        ).build()

    }

    @Singleton
    @Provides
    fun provideHackerNewsDao(
        hackerNewsDatabase: MovieDatabase
    ): MovieDao {

        return hackerNewsDatabase.userDao()

    }

}