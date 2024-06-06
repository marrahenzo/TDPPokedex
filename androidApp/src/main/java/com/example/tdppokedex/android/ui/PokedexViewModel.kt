package com.example.tdppokedex.android.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tdppokedex.android.R
import com.example.tdppokedex.data.Pokedex
import com.example.tdppokedex.data.PokedexRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class PokedexViewModel(
    private val pokedexRepository: PokedexRepository,
    private val context: Context
) : ViewModel() {

    private val pokedex = MutableLiveData<Pokedex>()
    private val sharedPreferences =
        context.getSharedPreferences(context.getString(R.string.pokedex), MODE_PRIVATE)

    private val _screenState: MutableStateFlow<PokedexScreenState> = MutableStateFlow(
        PokedexScreenState.Loading
    )
    val screenState: Flow<PokedexScreenState> = _screenState

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("PokedexViewModel", "Error retrieving pokedex: ${throwable.message}")
        }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                pokedexRepository.getPokedex()
            }.onSuccess {
                if (it != null) {
                    pokedex.postValue(it)
                    _screenState.value = PokedexScreenState.ShowPokedex(it)

                    // Pasó más de 1 día? Renovar caché
                    val lastUpdated = sharedPreferences.getLong(
                        context.getString(R.string.last_updated),
                        1
                    )
                    if (LocalDate.now().toEpochDay() - lastUpdated != 0L) {
                        pokedexRepository.deleteAllPokemon()
                        pokedexRepository.insertPokemon(it)
                        with(sharedPreferences.edit()) {
                            putLong(
                                context.getString(R.string.last_updated),
                                LocalDate.now().toEpochDay()
                            )
                            apply()
                        }
                    }
                } else {
                    _screenState.value = PokedexScreenState.Error
                }
            }.onFailure {
                Log.d("PokedexViewModel", "Error retrieving remote pokedex: ${it.message}")
                val pokemonEnCache = pokedexRepository.getAllPokemon()

                // Si hay pokemon en cache, los trae y sigue como si hubiese consumido la API
                if (pokemonEnCache.isNotEmpty()) {
                    val pokedexEnCache = Pokedex(
                        1302,
                        "https://pokeapi.co/api/v2/pokemon?offset=800&limit=502",
                        null,
                        pokemonEnCache
                    )
                    pokedex.postValue(pokedexEnCache)
                    _screenState.value = PokedexScreenState.ShowPokedex(pokedexEnCache)
                    Toast.makeText(context, "Pokédex obtenido desde caché", Toast.LENGTH_SHORT)
                        .show()
                } else
                    _screenState.value = PokedexScreenState.Error
            }

        }
    }

}
