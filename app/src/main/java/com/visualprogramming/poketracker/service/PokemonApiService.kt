package com.visualprogramming.poketracker.service

import com.visualprogramming.poketracker.model.PokemonApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface PokemonApiService {

    @GET("cards")
    suspend fun searchCards(
        @Query("search") query: String,
        @Query("limit") limit: Int = 20,
        @Header("Authorization") authHeader: String = "Bearer ${PokemonConfig.API_KEY}"
    ): Response<PokemonApiResponse>

    companion object {
        fun create(): PokemonApiService {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                    if (original.header("Authorization") == null && PokemonConfig.API_KEY.isNotBlank()) {
                        requestBuilder.header("Authorization", "Bearer ${PokemonConfig.API_KEY}")
                    }
                    chain.proceed(requestBuilder.build())
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(PokemonConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonApiService::class.java)
        }
    }
}

/**
 * Konfigurasi API Pokemon Card Price Tracker
 * Wajib mencantumkan API key sesuai instruksi soal.
 */
object PokemonConfig {
    const val BASE_URL = "https://www.pokemonpricetracker.com/api/v2/"

    // API Key dari akun https://www.pokemonpricetracker.com/
    // Cantumkan API key di sini:
    var API_KEY: String = "YOUR_API_KEY_HERE"
}
