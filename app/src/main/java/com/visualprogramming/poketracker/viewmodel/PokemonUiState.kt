package com.visualprogramming.poketracker.viewmodel

import com.visualprogramming.poketracker.model.PokemonCard

data class PokemonUiState(
    val cards: List<PokemonCard> = emptyList(),
    val selectedCard: PokemonCard? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "Charizard",
    val quickChips: List<String> = listOf("Charizard", "Pikachu", "Mewtwo", "Gengar", "Blastoise", "Rayquaza"),
    val selectedChip: String = "Charizard"
) {
    val isError: Boolean
        get() = errorMessage != null && cards.isEmpty()

    val marketResultsCount: Int
        get() = cards.size
}
