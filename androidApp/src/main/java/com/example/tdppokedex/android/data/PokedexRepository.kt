package com.example.tdppokedex.android.data

import com.example.tdppokedex.android.domain.Pokedex
import retrofit2.Response

interface PokedexRepository {

    suspend fun getPokedex() : Response<Pokedex>
}