package com.example.tdppokedex.android.data

import com.example.tdppokedex.android.domain.Pokedex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokedexRepositoryImp(private val pokedexClient: PokedexClient) : PokedexRepository {

    override suspend fun getPokedex(): Response<Pokedex> {
        return withContext(Dispatchers.IO) {
            pokedexClient.get()
        }
    }
}