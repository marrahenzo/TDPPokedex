package com.example.tdppokedex.data

interface PokedexRepository {

    suspend fun getPokedex(): Pokedex
}