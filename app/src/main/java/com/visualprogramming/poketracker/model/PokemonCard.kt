package com.visualprogramming.poketracker.model

import com.google.gson.annotations.SerializedName
import java.util.Locale

data class PokemonCard(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("tcgPlayerId")
    val tcgPlayerId: String? = null,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("setName")
    val setName: String = "",

    @SerializedName("cardNumber")
    val cardNumber: String = "",

    @SerializedName("rarity")
    val rarity: String? = null,

    @SerializedName("imageCdnUrl400")
    val imageCdnUrl400: String? = null,

    @SerializedName("imageCdnUrl200")
    val imageCdnUrl200: String? = null,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("prices")
    val prices: CardPrices? = null,

    @SerializedName("types")
    val types: List<String>? = null,

    @SerializedName("supertype")
    val supertype: String? = null,

    @SerializedName("subtypes")
    val subtypes: List<String>? = null,

    @SerializedName("hp")
    val hp: String? = null,

    @SerializedName("artist")
    val artist: String? = null,

    @SerializedName("illustrator")
    val illustrator: String? = null
) {
    val displayImageUrl: String?
        get() = imageCdnUrl400 ?: imageCdnUrl200 ?: imageUrl

    val formattedMarketPrice: String
        get() {
            val p = prices?.market ?: 0.0
            return String.format(Locale.US, "%.2f", p)
        }

    val formattedLowPrice: String
        get() {
            val p = prices?.low ?: 0.0
            return String.format(Locale.US, "%.2f", p)
        }

    val displayRarity: String
        get() = rarity?.takeIf { it.isNotBlank() } ?: "Promo"

    val displayType: String
        get() = types?.firstOrNull()?.takeIf { it.isNotBlank() }
            ?: supertype?.takeIf { it.isNotBlank() }
            ?: "Fire"

    val displayIllustrator: String
        get() = artist?.takeIf { it.isNotBlank() }
            ?: illustrator?.takeIf { it.isNotBlank() }
            ?: "Eske Yoshinob"

    val displaySubtitle: String
        get() = if (cardNumber.isNotBlank()) "$setName • #$cardNumber" else setName
}
