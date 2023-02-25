package com.example.avengersassemble.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(

    @SerializedName("status")
    var status: Boolean,

    @SerializedName("user_id")
    var userId: String,


)




