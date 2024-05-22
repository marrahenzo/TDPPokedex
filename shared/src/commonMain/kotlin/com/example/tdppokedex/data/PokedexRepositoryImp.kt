package com.example.tdppokedex.data

import com.example.tdppokedex.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json
import io.ktor.util.logging.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class PokedexRepositoryImp() : PokedexRepository {
    val pokedexClient = HttpClient {
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

    override suspend fun getPokedex(): Pokedex {
        return withContext(Dispatchers.IO) {
            pokedexClient.get("https://pokeapi.co/api/v2/pokemon") {
                parameter("limit", "800")
            }.body<Pokedex>()
        }
    }
}