package com.example.avengersassemble.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "favourite_table") // User Entity represents a table within the database.
data class FavouriteList(
    @PrimaryKey(autoGenerate = true)
    val id: Int, // <- 'id' is the primary key which will be autogenerated by the Room library.

    var name: String,
    var realname: String,
    var team: String,
    var firstappearance: String,
    var createdby: String,
    var publisher: String,
    var imageurl: String,
    var bio: String,
    var isFavourite: Boolean,

    ): Serializable {

    companion object {
        private const val serialVersionUID: Long = 123
    }

}