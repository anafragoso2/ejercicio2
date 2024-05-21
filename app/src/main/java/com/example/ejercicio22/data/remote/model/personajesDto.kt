package com.example.ejercicio22.data.remote.model

import com.google.gson.annotations.SerializedName

data class personajesDto(
    @SerializedName("_id")
    var _id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("photoUrl")
    var photoUrl: String,
    @SerializedName("affiliation")
    var affiliation: String,

    //var allies: List,
    //var enemies: List,


)
