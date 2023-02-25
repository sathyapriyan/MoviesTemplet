package com.example.avengersassemble.data.repository

import com.example.avengersassemble.data.remote.MovieApi
import com.example.avengersassemble.data.local.entity.FavouriteList
import com.example.avengersassemble.models.LoginResponse
import com.example.avengersassemble.models.requestbody.MarvelListResponse
import com.example.avengersassemble.models.requestbody.UserLoginCredentials
import com.example.avengersassemble.util.Constants
import com.example.avengersassemble.data.local.dao.MovieDao
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) {

    suspend fun userLoginRepo(
        userLoginCredentials: UserLoginCredentials
    ): Response<LoginResponse> {

        return movieApi.userLogin(userLoginCredentials = userLoginCredentials)

    }

    suspend fun fetchAllDetailsRepo(): Response<List<MarvelListResponse>> {
        val response: List<MarvelListResponse> = listOf()

        val result = movieApi.fetchAllDetails(Constants.MARVEL_LIST_URL)

        result?.body()?.let {

            it.forEach {
                movieDao.addData(
                    FavouriteList(
                        id=0,
                        name = it.name,
                        realname = it.realname ,
                        team= it.team,
                        firstappearance= it.firstappearance,
                        createdby= it.createdby,
                        publisher=it.publisher,
                        imageurl= it.imageurl,
                        bio=it.bio,
                        isFavourite= false,
                    ))
            }
        }

        return result

    }
    suspend fun readAllData():List<FavouriteList> = movieDao.readAllData()

    suspend fun addUser(user: FavouriteList) {
        movieDao.addData(user)
    }

    suspend fun updateUser(user: FavouriteList) {
        movieDao.updateData(user)
    }

    suspend fun deleteUser(user: FavouriteList) {
        movieDao.deletedata(user)
    }

    suspend fun deleteAllUsers() {
        movieDao.deleteAllData()
    }

}