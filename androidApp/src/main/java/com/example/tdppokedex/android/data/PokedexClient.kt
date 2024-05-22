package com.example.tdppokedex.android.data

import com.example.tdppokedex.data.Pokedex
import retrofit2.Response
import retrofit2.http.GET

//TODO: ver si se borra
interface PokedexClient {

    @GET("pokemon/?limit=800")
    suspend fun get(): Response<Pokedex>

}

