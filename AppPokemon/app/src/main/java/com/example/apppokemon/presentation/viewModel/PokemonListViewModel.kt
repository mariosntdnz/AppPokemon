package com.example.apppokemon.presentation.viewModel

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppokemon.R
import com.example.apppokemon.data.api.repository.PokemonRepository
import com.example.pokemon.common.Response
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel(){

    val pokemonRepository =  PokemonRepository()
    val responsePokemonListAllNames = MutableLiveData<Response>()

    fun getPokemonListAllNames(){
        viewModelScope.launch {
            try{
                val requisicao = pokemonRepository.getListAllNamesPokemon()
                responsePokemonListAllNames.postValue(Response.success(requisicao.body()))
            }
            catch (t : Throwable){
                println(t.message + "  getInfoPokemon viewmodel  ")
                responsePokemonListAllNames.postValue(Response.error(t))
            }
        }
    }



}