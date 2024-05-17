package com.example.tdppokedex.android.data

import com.example.tdppokedex.android.domain.Pokedex
import retrofit2.Response
import retrofit2.http.GET

interface PokedexClient {

    @GET("pokemon/?limit=800")
    suspend fun get() : Response<Pokedex>

}

