package com.example.avengersassemble.data.remote

import com.example.avengersassemble.models.LoginResponse
import com.example.avengersassemble.models.requestbody.MarvelListResponse
import com.example.avengersassemble.models.requestbody.UserLoginCredentials
import com.example.avengersassemble.util.Constants

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface MovieApi {

    @POST(Constants.LOGIN_URL)
    suspend fun userLogin(
        @Body userLoginCredentials: UserLoginCredentials
    ): Response<LoginResponse>

   @POST()
    suspend fun fetchAllDetails(
       @Url url: String
    ): Response<List<MarvelListResponse>>


}