package io.jhnplotim.mobile.stockmarketapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.jhnplotim.mobile.stockmarketapp.data.csv.CSVParser
import io.jhnplotim.mobile.stockmarketapp.data.csv.CompanyListingsParser
import io.jhnplotim.mobile.stockmarketapp.data.repository.StockRepositoryImpl
import io.jhnplotim.mobile.stockmarketapp.domain.model.CompanyListing
import io.jhnplotim.mobile.stockmarketapp.domain.repository.StockRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingParser: CompanyListingsParser
    ): CSVParser<CompanyListing>


    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}