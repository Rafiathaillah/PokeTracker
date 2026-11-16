package com.visualprogramming.poketracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.visualprogramming.poketracker.ui.screen.PokemonScreen
import com.visualprogramming.poketracker.ui.theme.PokeDarkBackground
import com.visualprogramming.poketracker.ui.theme.PokeTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = PokeDarkBackground
                ) {
                    PokemonScreen()
                }
            }
        }
    }
}
