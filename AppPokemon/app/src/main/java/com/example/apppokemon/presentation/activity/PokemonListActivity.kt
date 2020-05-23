package com.example.apppokemon.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppokemon.R
import com.example.apppokemon.data.api.model.Pokemon
import com.example.apppokemon.data.api.model.PokemonListAllNames
import com.example.apppokemon.presentation.adapter.PokemonListAdapter
import com.example.apppokemon.presentation.viewModel.PokemonListViewModel
import com.example.pokemon.common.Response
import com.example.pokemon.common.Status
import kotlinx.android.synthetic.main.activity_pokemon_list.*

class PokemonListActivity : AppCompatActivity() {

    private val viewModel = PokemonListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        viewModel.responsePokemonListAllNames.observe(this, Observer {response-> processResponse(response)})
        viewModel.getPokemonListAllNames()
        imageView_pesquisar_pokemon.setOnClickListener {
            val pokemons = viewModel.responsePokemonListAllNames.value?.data as PokemonListAllNames
            pokemons.let {
                setAdapter(it,true)
            }
        }
    }

    private fun processResponse(response: Response) {
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("processResponse error")
        }
    }
    private fun responseSuccess(result: Any?){
        result as PokemonListAllNames
        setAdapter(result,false)
        Toast.makeText(this,"Sucesso",Toast.LENGTH_SHORT).show()
    }
    private fun responseFailure(error: Throwable?){
        Toast.makeText(this,error?.message?:"Falha",Toast.LENGTH_SHORT).show()
    }
    private fun setAdapter(result : PokemonListAllNames,filter : Boolean){
        val r_view_pokemon_list = findViewById<RecyclerView>(R.id.recyclerView_list_pokemons)
        val editTextPesquisarPokemon = findViewById<EditText>(R.id.EditText_pesquisar_pokemon)
        val pokemons = getPokemonList(result,filter,if (filter) editTextPesquisarPokemon.text.toString() else null)

        r_view_pokemon_list.adapter = PokemonListAdapter(pokemons,this)
        r_view_pokemon_list.layoutManager = GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false)
        r_view_pokemon_list.visibility = View.VISIBLE
    }
    private fun getPokemonList(pokemonListAllNames : PokemonListAllNames, filterOn : Boolean,filter: String? ) =
        if(filterOn) getPokemonListFiltredNames(pokemonListAllNames,filter!!) else getPokemonListAllNames(pokemonListAllNames)

    private fun getPokemonListAllNames(pokemonListAllNames: PokemonListAllNames) : ArrayList<Pokemon>{
        val pokemonList = ArrayList<Pokemon>()

        pokemonListAllNames.results?.map {pokemonResultAllNames->
            val id= pokemonResultAllNames?.url?.removePrefix("https://pokeapi.co/api/v2/pokemon/")?.removeSuffix("/")?.toInt()
            val name = pokemonResultAllNames?.name
            if (id == null || name == null) throw Exception("Erro linha " + Thread.currentThread().getStackTrace()[0].getLineNumber().toString() + "\n")
            pokemonList.add(Pokemon(id,name))
        } ?: throw Exception("Erro linha " + Thread.currentThread().getStackTrace()[0].getLineNumber().toString() + "\n" + "Lista de Pokemons nula\n")

        return pokemonList
    }
    private fun getPokemonListFiltredNames(pokemonListAllNames: PokemonListAllNames,filter : String) : ArrayList<Pokemon>{
        val pokemonList = ArrayList<Pokemon>()

        pokemonListAllNames.results?.map {pokemonResultAllNames->
            val id= pokemonResultAllNames?.url?.removePrefix("https://pokeapi.co/api/v2/pokemon/")?.removeSuffix("/")?.toInt()
            val name= pokemonResultAllNames?.name
            if (id == null || name == null) throw Exception("Erro linha " + Thread.currentThread().getStackTrace()[0].getLineNumber().toString() + "\n")
            if(filter.length <= name!!.length && name?.substring(0,filter.length) == filter) {
                pokemonList.add(Pokemon(id,name))
            }
        } ?: throw Exception("Erro linha " + Thread.currentThread().getStackTrace()[0].getLineNumber().toString() + "\n" + "Lista de Pokemons nula\n")

        return pokemonList
    }
}
