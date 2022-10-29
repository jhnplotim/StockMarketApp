package io.jhnplotim.mobile.stockmarketapp.domain.repository

import io.jhnplotim.mobile.stockmarketapp.domain.model.CompanyListing
import io.jhnplotim.mobile.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}