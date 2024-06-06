package com.example.tdppokedex.android.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tdppokedex.DatabaseDriverFactory
import com.example.tdppokedex.data.PokedexRepositoryImp

class PokedexViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pokedexRepository = PokedexRepositoryImp(DatabaseDriverFactory(context))

        return PokedexViewModel(pokedexRepository, context) as T
    }
}