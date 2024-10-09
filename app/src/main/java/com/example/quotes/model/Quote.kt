package com.example.quotes.model

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

data class Quote(
    @SerializedName("quote") val quote: String, //lainaus
    @SerializedName("author") val author: String //lainauksen kirjoittaja
)

const val QUOTE_BASE_URL = "https://api.api-ninjas.com/v1/"

interface QuotesApi {
    @GET("quotes?category=happiness") // Hakee lainauksia happiness-kategoriasta
    suspend fun getQuote(@Header("X-Api-Key") apiKey: String): List<Quote> // Hakee lainauksen API:sta

    companion object {
        private var quotesService: QuotesApi? = null

        // Luo instanssin QuotesApi:sta
        fun getInstance(): QuotesApi {
            if (quotesService == null) {
                quotesService = Retrofit.Builder()
                    .baseUrl(QUOTE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(QuotesApi::class.java)
            }
            return quotesService!!
        }
    }
}