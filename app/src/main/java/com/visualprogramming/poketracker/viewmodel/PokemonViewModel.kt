package com.visualprogramming.poketracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogramming.poketracker.data.PokemonRepository
import com.visualprogramming.poketracker.model.PokemonCard
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonUiState(isLoading = true))
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        // Initial search for Charizard as shown in screenshot
        searchCards("Charizard")
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { it.copy(searchQuery = newQuery) }
    }

    fun onSearchSubmitted(query: String) {
        val target = query.trim().ifBlank { "Charizard" }
        _uiState.update {
            it.copy(
                searchQuery = target,
                selectedChip = if (it.quickChips.any { chip -> chip.equals(target, ignoreCase = true) }) target else ""
            )
        }
        searchCards(target)
    }

    fun onChipSelected(chip: String) {
        _uiState.update {
            it.copy(
                searchQuery = chip,
                selectedChip = chip
            )
        }
        searchCards(chip)
    }

    fun clearSearch() {
        _uiState.update { it.copy(searchQuery = "", selectedChip = "") }
    }

    fun onCardSelected(card: PokemonCard) {
        _uiState.update { it.copy(selectedCard = card) }
    }

    fun dismissCardDetail() {
        _uiState.update { it.copy(selectedCard = null) }
    }

    fun retry() {
        searchCards(_uiState.value.searchQuery.ifBlank { "Charizard" })
    }

    private fun searchCards(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            val result = repository.searchCards(query)

            result.fold(
                onSuccess = { cards ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            cards = cards,
                            errorMessage = null
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            cards = emptyList(),
                            errorMessage = error.message ?: "Connection Failed"
                        )
                    }
                }
            )
        }
    }
}
