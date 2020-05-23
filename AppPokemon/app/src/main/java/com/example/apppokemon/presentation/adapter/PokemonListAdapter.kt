package com.example.apppokemon.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppokemon.R
import com.example.apppokemon.data.api.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_view_inflar_pokemon_list.view.*

class PokemonListAdapter(private val pokemons : ArrayList<Pokemon>,
                        private val context : Context
                        ) : RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>(){

    private val picasso = Picasso.get()
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_inflar_pokemon_list, parent, false))
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pokemon= pokemons[position]

        picasso.load("https://pokeres.bastionbot.org/images/pokemon/${pokemon.id}.png").into(holder.itemView.imageView_foto_pokemon)
        holder.itemView.textView_nome_pokemon.text = pokemon.nome
    }

}