package com.example.apppokemon.data.api.repository

import com.example.apppokemon.data.api.RestService
import com.example.apppokemon.data.api.model.PokemonListAllNames
import retrofit2.Response

class PokemonRepository {
    private val api = RestService
    suspend fun getListAllNamesPokemon(): Response<PokemonListAllNames> {
        try {
            val response = api.getRetrofit().getPokemonListNames()
            if (!response.isSuccessful){
                throw Exception("Erro ao fazer requisição")
            }
            else {
                return response
            }
        }
        catch (e : Exception){
            println(e.message)
            throw Exception(e.message)
        }
    }
}