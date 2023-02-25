package com.example.avengersassemble.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.avengersassemble.data.local.dao.MovieDao
import com.example.avengersassemble.data.local.entity.FavouriteList

// UserDatabase represents database and contains the database holder and server the main access point for the underlying connection to your app's persisted, relational data.

@Database(
    entities = [FavouriteList::class],
    version = 1,                // <- Database version
    exportSchema = true
)
abstract class MovieDatabase: RoomDatabase() { // <- Add 'abstract' keyword and extends RoomDatabase
    abstract fun userDao(): MovieDao
}