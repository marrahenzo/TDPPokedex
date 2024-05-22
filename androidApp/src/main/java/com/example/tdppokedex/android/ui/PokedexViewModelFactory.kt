package com.example.tdppokedex.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tdppokedex.data.PokedexRepositoryImp

class PokedexViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pokedexRepository = PokedexRepositoryImp()

        return PokedexViewModel(pokedexRepository) as T
    }
}