package com.visualprogramming.poketracker.data

import com.visualprogramming.poketracker.model.CardPrices
import com.visualprogramming.poketracker.model.PokemonCard
import com.visualprogramming.poketracker.service.PokemonApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(
    private val apiService: PokemonApiService = PokemonApiService.create()
) {

    /**
     * Set ke true jika ingin menggunakan mock data ketika API request gagal
     * (misal: jika kuota 100 request/hari habis atau belum memasukkan API key).
     * Jika false, error seperti "HTTP 401" akan langsung ditampilkan ke UI sesuai screenshot Soal 1.
     */
    var useMockFallbackOnError: Boolean = false

    suspend fun searchCards(query: String, limit: Int = 20): Result<List<PokemonCard>> {
        return withContext(Dispatchers.IO) {
            try {
                val cleanQuery = query.trim().ifBlank { "Charizard" }
                val response = apiService.searchCards(cleanQuery, limit)

                if (response.isSuccessful) {
                    val cards = response.body()?.data ?: emptyList()
                    if (cards.isEmpty()) {
                        Result.failure(Exception("No cards found for \"$cleanQuery\""))
                    } else {
                        Result.success(cards)
                    }
                } else {
                    val errorCode = response.code()
                    val errorMessage = "HTTP $errorCode"

                    if (useMockFallbackOnError) {
                        Result.success(getMockCards(cleanQuery))
                    } else {
                        Result.failure(Exception(errorMessage))
                    }
                }
            } catch (e: Exception) {
                if (useMockFallbackOnError) {
                    Result.success(getMockCards(query))
                } else {
                    val message = e.message ?: "Connection Failed"
                    Result.failure(Exception(message))
                }
            }
        }
    }

    /**
     * Mock data yang sesuai dengan contoh screenshot di Soal 1
     */
    fun getMockCards(query: String = "Charizard"): List<PokemonCard> {
        val q = query.lowercase().trim()
        val allCards = listOf(
            PokemonCard(
                id = "charizard-xy29",
                tcgPlayerId = "96213",
                name = "Charizard EX - XY29",
                setName = "XY Promos",
                cardNumber = "XY29",
                rarity = "Promo",
                imageCdnUrl400 = "https://images.pokemontcg.io/xyp/XY29_hires.png",
                imageCdnUrl200 = "https://images.pokemontcg.io/xyp/XY29.png",
                prices = CardPrices(
                    market = 21.78,
                    low = 2.84,
                    mid = 25.00,
                    high = 45.00,
                    listings = 109,
                    sellers = 88,
                    primaryPrinting = "Holofoil",
                    lastUpdated = "2026-11-16T12:00:00Z"
                ),
                types = listOf("Fire"),
                supertype = "Pokémon",
                subtypes = listOf("Basic", "EX"),
                hp = "180",
                artist = "Eske Yoshinob",
                illustrator = "Eske Yoshinob"
            ),
            PokemonCard(
                id = "charizard-xy17",
                tcgPlayerId = "91157",
                name = "Charizard EX - XY17",
                setName = "XY Promos",
                cardNumber = "XY17",
                rarity = "Promo",
                imageCdnUrl400 = "https://images.pokemontcg.io/xyp/XY17_hires.png",
                imageCdnUrl200 = "https://images.pokemontcg.io/xyp/XY17.png",
                prices = CardPrices(
                    market = 37.44,
                    low = 8.50,
                    mid = 40.00,
                    high = 70.00,
                    listings = 84,
                    sellers = 67,
                    primaryPrinting = "Holofoil",
                    lastUpdated = "2026-11-16T12:00:00Z"
                ),
                types = listOf("Fire"),
                supertype = "Pokémon",
                subtypes = listOf("Basic", "EX"),
                hp = "180",
                artist = "Eske Yoshinob",
                illustrator = "Eske Yoshinob"
            ),
            PokemonCard(
                id = "charizard-xy29-jumbo",
                tcgPlayerId = "96214",
                name = "Charizard EX (Oversize) - XY29",
                setName = "XY Promos",
                cardNumber = "XY29",
                rarity = "Promo",
                imageCdnUrl400 = "https://images.pokemontcg.io/xyp/XY29_hires.png",
                imageCdnUrl200 = "https://images.pokemontcg.io/xyp/XY29.png",
                prices = CardPrices(
                    market = 15.50,
                    low = 4.20,
                    mid = 18.00,
                    high = 30.00,
                    listings = 42,
                    sellers = 35,
                    primaryPrinting = "Holofoil",
                    lastUpdated = "2026-11-16T12:00:00Z"
                ),
                types = listOf("Fire"),
                supertype = "Pokémon",
                subtypes = listOf("Basic", "EX"),
                hp = "180",
                artist = "Eske Yoshinob",
                illustrator = "Eske Yoshinob"
            ),
            PokemonCard(
                id = "m-charizard-ex-12",
                tcgPlayerId = "84189",
                name = "M Charizard EX - 12/106",
                setName = "XY - Flashfire",
                cardNumber = "12/106",
                rarity = "Ultra Rare",
                imageCdnUrl400 = "https://images.pokemontcg.io/xy2/12_hires.png",
                imageCdnUrl200 = "https://images.pokemontcg.io/xy2/12.png",
                prices = CardPrices(
                    market = 52.80,
                    low = 19.99,
                    mid = 55.00,
                    high = 95.00,
                    listings = 95,
                    sellers = 72,
                    primaryPrinting = "Holofoil",
                    lastUpdated = "2026-11-16T12:00:00Z"
                ),
                types = listOf("Fire"),
                supertype = "Pokémon",
                subtypes = listOf("MEGA", "EX"),
                hp = "220",
                artist = "5ban Graphics",
                illustrator = "5ban Graphics"
            ),
            PokemonCard(
                id = "pikachu-ex-xy84",
                tcgPlayerId = "118228",
                name = "Pikachu EX - XY84",
                setName = "XY Promos",
                cardNumber = "XY84",
                rarity = "Promo",
                imageCdnUrl400 = "https://images.pokemontcg.io/xyp/XY84_hires.png",
                imageCdnUrl200 = "https://images.pokemontcg.io/xyp/XY84.png",
                prices = CardPrices(
                    market = 42.10,
                    low = 12.00,
                    mid = 45.00,
                    high = 80.00,
                    listings = 65,
                    sellers = 50,
                    primaryPrinting = "Holofoil",
                    lastUpdated = "2026-11-16T12:00:00Z"
                ),
                types = listOf("Lightning"),
                supertype = "Pokémon",
                subtypes = listOf("Basic", "EX"),
                hp = "130",
                artist = "Hitoshi Ariga",
                illustrator = "Hitoshi Ariga"
            ),
            PokemonCard(
                id = "mewtwo-ex-xy107",
                tcgPlayerId = "120894",
                name = "Mewtwo EX - XY107",
                setName = "XY Promos",
                cardNumber = "XY107",
                rarity = "Promo",
                imageCdnUrl400 = "https://images.pokemontcg.io/xyp/XY107_hires.png",
                imageCdnUrl200 = "https://images.pokemontcg.io/xyp/XY107.png",
                prices = CardPrices(
                    market = 28.50,
                    low = 6.99,
                    mid = 30.00,
                    high = 50.00,
                    listings = 78,
                    sellers = 62,
                    primaryPrinting = "Holofoil",
                    lastUpdated = "2026-11-16T12:00:00Z"
                ),
                types = listOf("Psychic"),
                supertype = "Pokémon",
                subtypes = listOf("Basic", "EX"),
                hp = "170",
                artist = "Eske Yoshinob",
                illustrator = "Eske Yoshinob"
            ),
            PokemonCard(
                id = "gengar-ex-34",
                tcgPlayerId = "94065",
                name = "Gengar EX - 34/119",
                setName = "XY - Phantom Forces",
                cardNumber = "34/119",
                rarity = "Ultra Rare",
                imageCdnUrl400 = "https://images.pokemontcg.io/xy4/34_hires.png",
                imageCdnUrl200 = "https://images.pokemontcg.io/xy4/34.png",
                prices = CardPrices(
                    market = 18.90,
                    low = 4.50,
                    mid = 20.00,
                    high = 35.00,
                    listings = 92,
                    sellers = 70,
                    primaryPrinting = "Holofoil",
                    lastUpdated = "2026-11-16T12:00:00Z"
                ),
                types = listOf("Psychic"),
                supertype = "Pokémon",
                subtypes = listOf("Basic", "EX"),
                hp = "170",
                artist = "Eske Yoshinob",
                illustrator = "Eske Yoshinob"
            )
        )

        return if (q.isBlank()) {
            allCards
        } else {
            val filtered = allCards.filter { it.name.contains(q, ignoreCase = true) || it.setName.contains(q, ignoreCase = true) }
            if (filtered.isNotEmpty()) filtered else allCards
        }
    }
}
