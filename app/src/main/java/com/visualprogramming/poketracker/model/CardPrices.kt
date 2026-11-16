package com.visualprogramming.poketracker.model

import com.google.gson.annotations.SerializedName

data class CardPrices(
    @SerializedName("market")
    val market: Double? = null,

    @SerializedName("low")
    val low: Double? = null,

    @SerializedName("mid")
    val mid: Double? = null,

    @SerializedName("high")
    val high: Double? = null,

    @SerializedName("listings")
    val listings: Int? = null,

    @SerializedName("sellers")
    val sellers: Int? = null,

    @SerializedName("primaryPrinting")
    val primaryPrinting: String? = null,

    @SerializedName("lastUpdated")
    val lastUpdated: String? = null
)
