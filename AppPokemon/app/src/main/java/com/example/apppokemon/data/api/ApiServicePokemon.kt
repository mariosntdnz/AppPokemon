package com.example.apppokemon.data.api

import com.example.apppokemon.data.api.model.Pokemon
import com.example.apppokemon.data.api.model.PokemonListAllNames
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServicePokemon {

    @GET("pokemon/?limit=964")
    suspend fun getPokemonListNames() : Response<PokemonListAllNames>

    @GET("/pokemon/{name}")
    suspend fun getPokemon(@Path("name") name : String) : Response<Pokemon>
}