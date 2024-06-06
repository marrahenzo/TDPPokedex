package com.example.tdppokedex.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String
)
