package com.example.avengersassemble.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avengersassemble.data.remote.NetworkResult
import com.example.avengersassemble.data.repository.MovieRepository
import com.example.avengersassemble.models.LoginResponse
import com.example.avengersassemble.models.requestbody.UserLoginCredentials
import com.example.avengersassemble.util.CommonUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val MovieRepository: MovieRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _authenticationResponse: MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()
    val authenticationResponse = _authenticationResponse

    fun authenticatePatient(mobileNum: String, password: String, context: Context) {

        println("Username and Password --> $mobileNum and $password")

        _authenticationResponse.value = NetworkResult.Loading()

        if (CommonUtil.hasInternetConnection(context = context)) {

            viewModelScope.launch(ioDispatcher) {

                try {

                    val response = MovieRepository.userLoginRepo(
                        userLoginCredentials= UserLoginCredentials(
                            mobileNumber = mobileNum,
                            password = password
                        )
                    )

                    if (response.isSuccessful){

                        _authenticationResponse.postValue(
                            NetworkResult.Success(
                                data = response.body()!!
                            )
                        )

                    } else {

                        _authenticationResponse.postValue(NetworkResult.Error(response.message()))

                    }

                } catch (exception: Exception) {

                    _authenticationResponse.postValue(NetworkResult.Error(exception.message))

                }

            }

        } else {

            _authenticationResponse.value = NetworkResult.Error("No Internet Connection!")

        }

    }

}