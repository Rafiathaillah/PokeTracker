package com.visualprogramming.poketracker.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.visualprogramming.poketracker.ui.components.CategoryChips
import com.visualprogramming.poketracker.ui.components.ErrorConnectionView
import com.visualprogramming.poketracker.ui.components.PokemonCardItem
import com.visualprogramming.poketracker.ui.components.PokemonDetailDialog
import com.visualprogramming.poketracker.ui.components.SearchBarComponent
import com.visualprogramming.poketracker.ui.components.TopHeaderBar
import com.visualprogramming.poketracker.ui.theme.PokeDarkBackground
import com.visualprogramming.poketracker.ui.theme.PokeGreen
import com.visualprogramming.poketracker.ui.theme.Slate400
import com.visualprogramming.poketracker.viewmodel.PokemonViewModel

@Composable
fun PokemonScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = PokeDarkBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(PokeDarkBackground)
        ) {
            // 1. Top Header Bar
            TopHeaderBar()

            // 2. Search Bar
            SearchBarComponent(
                query = uiState.searchQuery,
                onQueryChange = { viewModel.onSearchQueryChanged(it) },
                onSearchSubmit = { viewModel.onSearchSubmitted(it) },
                onClear = { viewModel.clearSearch() },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 3. Filter Chips
            CategoryChips(
                chips = uiState.quickChips,
                selectedChip = uiState.selectedChip,
                onChipSelect = { viewModel.onChipSelected(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Main Body: Loading / Error / Results Grid
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = PokeGreen,
                                strokeWidth = 3.dp
                            )
                        }
                    }

                    uiState.isError -> {
                        ErrorConnectionView(
                            errorMessage = uiState.errorMessage ?: "HTTP 401",
                            onRetry = { viewModel.retry() }
                        )
                    }

                    else -> {
                        Column(modifier = Modifier.fillMaxSize()) {
                            // Sub-header Hasil Pencarian
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Market Results (${uiState.marketResultsCount})",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "USD • Near Mint",
                                    color = Slate400,
                                    fontSize = 12.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // 2-Column Scrollable Grid Card Pokémon
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(
                                    items = uiState.cards,
                                    key = { it.id ?: it.tcgPlayerId ?: "${it.name}-${it.cardNumber}" }
                                ) { card ->
                                    PokemonCardItem(
                                        card = card,
                                        onClick = { viewModel.onCardSelected(card) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 5. Card Detail Modal / Dialog
        uiState.selectedCard?.let { card ->
            PokemonDetailDialog(
                card = card,
                onDismiss = { viewModel.dismissCardDetail() }
            )
        }
    }
}
