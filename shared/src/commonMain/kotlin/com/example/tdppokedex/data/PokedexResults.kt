package com.example.tdppokedex.data

import kotlinx.serialization.SerialName

@Serializable
data class PokedexResults(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String
)
