package com.visualprogramming.poketracker.model

import com.google.gson.annotations.SerializedName

data class PokemonApiResponse(
    @SerializedName("data")
    val data: List<PokemonCard>? = null,

    @SerializedName("metadata")
    val metadata: PokemonApiMetadata? = null,

    @SerializedName("error")
    val error: String? = null,

    @SerializedName("hint")
    val hint: String? = null
)

data class PokemonApiMetadata(
    @SerializedName("total")
    val total: Int? = null,

    @SerializedName("count")
    val count: Int? = null
)
