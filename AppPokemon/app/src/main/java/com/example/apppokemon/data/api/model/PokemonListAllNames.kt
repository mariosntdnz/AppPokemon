package com.example.apppokemon.data.api.model

import com.google.gson.annotations.SerializedName

class PokemonListAllNames (
    val count : Int?,
    val results : List<PokemonResultName?>?
    )