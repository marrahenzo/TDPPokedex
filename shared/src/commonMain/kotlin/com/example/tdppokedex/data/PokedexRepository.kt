package com.example.tdppokedex.data

interface PokedexRepository {

    suspend fun getPokedex(): Pokedex
    suspend fun insertPokemon(pokedex: Pokedex): Unit
    suspend fun deleteAllPokemon(): Unit
    suspend fun deletePokemon(name: String): Unit
    suspend fun getAllPokemon(): List<Pokemon>
    suspend fun getPokemon(name: String): Pokemon
}