package com.example.tdppokedex.data

import kotlinx.serialization.SerialName

@Serializable
data class Pokedex(
    @SerialName(value = "count")
    val count: Int,
    @SerialName(value = "next")
    val next: String,
    @SerialName(value = "previous")
    val previous: String?,
    @SerialName(value = "results")
    val results: List<PokedexResults>
)
