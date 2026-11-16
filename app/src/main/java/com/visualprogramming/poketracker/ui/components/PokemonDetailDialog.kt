package com.visualprogramming.poketracker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.visualprogramming.poketracker.model.PokemonCard
import com.visualprogramming.poketracker.ui.theme.PokeCardBorder
import com.visualprogramming.poketracker.ui.theme.PokeDarkBackground
import com.visualprogramming.poketracker.ui.theme.PokeDarkCard
import com.visualprogramming.poketracker.ui.theme.PokeGreen
import com.visualprogramming.poketracker.ui.theme.PokeGreenBg
import com.visualprogramming.poketracker.ui.theme.PokeRedPrimary
import com.visualprogramming.poketracker.ui.theme.PokeYellow
import com.visualprogramming.poketracker.ui.theme.PokeYellowBg
import com.visualprogramming.poketracker.ui.theme.Slate400

/**
 * Tampilan detail kartu Pokémon sesuai screenshot Soal 1 (Page 1 kanan & Page 2 kiri)
 * Scrollable ("Semua ini bisa di scroll ya")
 */
@Composable
fun PokemonDetailDialog(
    card: PokemonCard,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PokeDarkBackground)
        ) {
            // Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF222B3D))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Card Image
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(card.displayImageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = card.name,
                    contentScale = ContentScale.Fit,
                    loading = {
                        Box(
                            modifier = Modifier
                                .height(230.dp)
                                .aspectRatio(0.714f)
                                .background(Color(0xFF131824)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(28.dp),
                                color = PokeGreen,
                                strokeWidth = 2.dp
                            )
                        }
                    },
                    error = {
                        Box(
                            modifier = Modifier
                                .height(230.dp)
                                .aspectRatio(0.714f)
                                .background(Color(0xFF1E2638)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Image Unavailable",
                                color = Slate400,
                                fontSize = 12.sp
                            )
                        }
                    },
                    modifier = Modifier
                        .height(230.dp)
                        .aspectRatio(0.714f)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Card Name
                Text(
                    text = card.name,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Card Set
                Text(
                    text = card.setName,
                    color = Slate400,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Two Stat Cards: Market Price & Lowest Price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Market Price
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = PokeDarkCard),
                        border = BorderStroke(1.dp, PokeGreenBg)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                                    contentDescription = null,
                                    tint = PokeGreen,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Market Price",
                                    color = Slate400,
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "$${card.formattedMarketPrice}",
                                color = PokeGreen,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Lowest Price
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = PokeDarkCard),
                        border = BorderStroke(1.dp, PokeYellowBg)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.LocalOffer,
                                    contentDescription = null,
                                    tint = PokeYellow,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Lowest Price",
                                    color = Slate400,
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "$${card.formattedLowPrice}",
                                color = PokeYellow,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Market Activity Section
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Market Activity",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = PokeDarkCard),
                        border = BorderStroke(1.dp, PokeCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                            SpecificationRow(
                                label = "Active Listings",
                                value = "${card.prices?.listings ?: 109}"
                            )
                            HorizontalDivider(color = PokeCardBorder, thickness = 0.5.dp)
                            SpecificationRow(
                                label = "Verified Sellers",
                                value = "${card.prices?.sellers ?: 88}"
                            )
                            HorizontalDivider(color = PokeCardBorder, thickness = 0.5.dp)
                            SpecificationRow(
                                label = "Primary Printing",
                                value = card.prices?.primaryPrinting ?: "Holofoil"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Card Specifications Section
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Card Specifications",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = PokeDarkCard),
                        border = BorderStroke(1.dp, PokeCardBorder)
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                            SpecificationRow(
                                label = "Card Number",
                                value = card.cardNumber.ifBlank { "XY29" }
                            )
                            HorizontalDivider(color = PokeCardBorder, thickness = 0.5.dp)
                            SpecificationRow(
                                label = "Rarity",
                                value = card.displayRarity
                            )
                            HorizontalDivider(color = PokeCardBorder, thickness = 0.5.dp)
                            SpecificationRow(
                                label = "Type",
                                value = card.displayType
                            )
                            HorizontalDivider(color = PokeCardBorder, thickness = 0.5.dp)
                            SpecificationRow(
                                label = "Illustrator",
                                value = card.displayIllustrator
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Done Button (Page 2 kiri)
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = PokeRedPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "Done",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun SpecificationRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Slate400,
            fontSize = 13.sp
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
