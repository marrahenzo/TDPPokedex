package com.example.tdppokedex.android.ui

import com.example.tdppokedex.data.Pokedex

sealed class PokedexScreenState {
    object Loading : PokedexScreenState()

    object Error : PokedexScreenState()

    class ShowPokedex(val pokedex: Pokedex) : PokedexScreenState()
}