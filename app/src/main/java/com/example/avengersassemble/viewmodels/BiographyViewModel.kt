package com.example.avengersassemble.viewmodels

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avengersassemble.data.remote.NetworkResult
import com.example.avengersassemble.data.repository.MovieRepository
import com.example.avengersassemble.data.local.entity.FavouriteList
import com.example.avengersassemble.models.LoginResponse
import com.example.avengersassemble.models.requestbody.UserLoginCredentials
import com.example.avengersassemble.util.CommonUtil
import com.example.avengersassemble.util.SharedPreferenceKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class BiographyViewModel @Inject constructor(
    private val MovieRepository: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher,
    @Named("userPreferences")
    private var preferencesDataStoreUser: DataStore<Preferences>

): ViewModel() {
    val userId: Flow<String> =  preferencesDataStoreUser.data.map { preferences->
        preferences[SharedPreferenceKeys.USER_ID] ?: ""
    }

    suspend fun addOrRemoveFavourite(data: FavouriteList, isFavourite:Boolean){
        if(isFavourite){
            MovieRepository.updateUser(FavouriteList(
                id = data.id,
                name = data.name,
                realname = data.realname,
                team = data.team,
                firstappearance = data.firstappearance,
                createdby = data.createdby,
                publisher = data.publisher,
                imageurl = data.imageurl,
                bio = data.bio,
                isFavourite = false
            ))
        }else{
            MovieRepository.updateUser(FavouriteList(
                id = data.id,
                name = data.name,
                realname = data.realname,
                team = data.team,
                firstappearance = data.firstappearance,
                createdby = data.createdby,
                publisher = data.publisher,
                imageurl = data.imageurl,
                bio = data.bio,
                isFavourite = true
            ))

        }
    }
}