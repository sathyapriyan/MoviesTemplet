package com.example.avengersassemble.data.local.dao

import androidx.room.*
import com.example.avengersassemble.data.local.entity.FavouriteList

// UserDao contains the methods used for accessing the database, including queries.
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // <- Annotate the 'addUser' function below. Set the onConflict strategy to IGNORE so if exactly the same user exists, it will just ignore it.
    suspend fun addData(user: FavouriteList)

    @Update
    suspend fun updateData(user: FavouriteList)

    @Delete
    suspend fun deletedata(user: FavouriteList)

    @Query("DELETE FROM favourite_table")
    suspend fun deleteAllData()

    @Query("SELECT * from favourite_table ORDER BY id ASC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): List<FavouriteList> // <- This means function return type is List. Specifically, a List of Users.
}