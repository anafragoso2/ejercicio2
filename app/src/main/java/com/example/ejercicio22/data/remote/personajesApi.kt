package com.example.ejercicio22.data.remote

import com.example.ejercicio22.data.remote.model.personajeDetail
import com.example.ejercicio22.data.remote.model.personajesDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface personajesApi {
    @GET("api/v1/characters?perPage=497")
    fun getpersonajesDto(
        //  @Url url: String
    ): Call<List<personajesDto>>

    @GET("api/v1/characters/{id}")
    fun getPersonajeDetail(
        @Path("id") id: String
    ): Call<personajeDetail>
    //getPersonajeDetail("5cf5679a915ecad153ab68cb")
}