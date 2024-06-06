package com.example.tdppokedex.data

import com.example.pokedex.Database
import com.example.pokedex.PokemonQueries
import com.example.tdppokedex.DatabaseDriverFactory
import com.example.tdppokedex.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class PokedexRepositoryImp(driverFactory: DatabaseDriverFactory) : PokedexRepository {
    private val pokedexClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HttpClient", message = message)
                }
            }
            logger
        }
    }.also { initLogger() }

    private val pokedexDB = Database(driverFactory.createDriver())
    private val query: PokemonQueries = pokedexDB.pokemonQueries

    override suspend fun getPokedex(): Pokedex {
        return withContext(Dispatchers.IO) {
            pokedexClient.get("https://pokeapi.co/api/v2/pokemon") {
                parameter("limit", "800")
            }.body<Pokedex>()
        }
    }

    override suspend fun insertPokemon(pokedex: Pokedex) {
        pokedexDB.transaction {
            for (pokemon in pokedex.results)
                query.insertPokemon(pokemon.name, pokemon.url)
        }
    }

    override suspend fun deleteAllPokemon() {
        query.deleteAllPokemon()
    }

    override suspend fun deletePokemon(name: String) {
        query.deletePokemon(name)
    }

    override suspend fun getAllPokemon(): List<Pokemon> {
        return query.getAllPokemon(::mapToPokemon).executeAsList()
    }

    override suspend fun getPokemon(name: String): Pokemon {
        return query.getPokemon(name, ::mapToPokemon).executeAsOne()
    }

    private fun mapToPokemon(name: String, url: String): Pokemon {
        return Pokemon(name, url)
    }
}