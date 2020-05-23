package com.example.apppokemon.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.apppokemon.R

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt_listPokemons = findViewById<Button>(R.id.button_listarPokemons)

        bt_listPokemons.setOnClickListener {
            val intent = Intent(this,PokemonListActivity::class.java)
            startActivity(intent)
        }
    }
}
