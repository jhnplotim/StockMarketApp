package io.jhnplotim.mobile.stockmarketapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.jhnplotim.mobile.stockmarketapp.data.local.CompanyListingEntity
import io.jhnplotim.mobile.stockmarketapp.data.local.StockDao

@Database(
    entities = [CompanyListingEntity::class],
    version = 1
)
abstract class StockDatabase: RoomDatabase() {
    abstract val dao: StockDao
}