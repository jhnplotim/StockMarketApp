package io.jhnplotim.mobile.stockmarketapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String
    ): ResponseBody


    companion object {
        // Disclaimer: Normally you would not put an API key within the source code
        const val API_KEY = "BRPQ9WS2LPPZ3PT7"
        const val BASE_URL = "https://alphavantage.co"
    }
}