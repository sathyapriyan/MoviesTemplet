package com.example.avengersassemble.models.requestbody

import com.google.gson.annotations.SerializedName

data class UserLoginCredentials(

    @SerializedName("mobile_number")
    var mobileNumber: String,

    @SerializedName("password")
    var password: String,


)
