package com.example.avengersassemble.models.requestbody

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MarvelListResponse(

    @SerializedName("name")
    var name: String,

    @SerializedName("realname")
    var realname: String,

    @SerializedName("team")
    var team: String,

    @SerializedName("firstappearance")
    var firstappearance: String,

    @SerializedName("createdby")
    var createdby: String,

    @SerializedName("publisher")
    var publisher: String,

    @SerializedName("imageurl")
    var imageurl: String,

    @SerializedName("bio")
    var bio: String,

): Serializable {

    companion object {
        private const val serialVersionUID: Long = 123
    }

}

