package com.example.avengersassemble.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avengersassemble.data.remote.NetworkResult
import com.example.avengersassemble.data.repository.MovieRepository
import com.example.avengersassemble.data.local.entity.FavouriteList
import com.example.avengersassemble.util.CommonUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _ListResponse: MutableLiveData<NetworkResult<List<FavouriteList>>> = MutableLiveData()
    val ListResponse = _ListResponse

    fun getAllDoctorsDetails(context: Context) {

        _ListResponse.value=NetworkResult.Loading()

        viewModelScope.launch(ioDispatcher) {
            if(repository.readAllData().isEmpty()){
                if (CommonUtil.hasInternetConnection(context = context)) {

                    viewModelScope.launch(ioDispatcher) {

                        try {

                            val response = repository.fetchAllDetailsRepo()
                            println(response)

                            if (response.isSuccessful){

                                val data =repository.readAllData()
                                _ListResponse.postValue(
                                    NetworkResult.Success(
                                        data = data
                                    )
                                )
                                println(response.body())

                            } else {

                                _ListResponse.postValue(NetworkResult.Error(response.message()))

                            }

                        } catch (exception: Exception) {

                            _ListResponse.postValue(NetworkResult.Error(exception.message))

                        }

                    }

                } else {

                    _ListResponse.value = NetworkResult.Error("No Internet Connection!")

                }

            }else{
                val data =repository.readAllData()
                _ListResponse.postValue(
                    NetworkResult.Success(
                        data = data
                    )
                )
            }

        }



    }

}