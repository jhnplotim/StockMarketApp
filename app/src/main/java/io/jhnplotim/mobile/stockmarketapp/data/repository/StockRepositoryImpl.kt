package io.jhnplotim.mobile.stockmarketapp.data.repository

import io.jhnplotim.mobile.stockmarketapp.data.csv.CSVParser
import io.jhnplotim.mobile.stockmarketapp.data.local.StockDatabase
import io.jhnplotim.mobile.stockmarketapp.data.mapper.toCompanyListing
import io.jhnplotim.mobile.stockmarketapp.data.mapper.toCompanyListingEntity
import io.jhnplotim.mobile.stockmarketapp.data.remote.StockApi
import io.jhnplotim.mobile.stockmarketapp.domain.model.CompanyListing
import io.jhnplotim.mobile.stockmarketapp.domain.repository.StockRepository
import io.jhnplotim.mobile.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListing = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            }  catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }
            catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data =  dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}