package io.jhnplotim.mobile.stockmarketapp.data.mapper

import io.jhnplotim.mobile.stockmarketapp.data.local.CompanyListingEntity
import io.jhnplotim.mobile.stockmarketapp.data.remote.dto.CompanyInfoDto
import io.jhnplotim.mobile.stockmarketapp.domain.model.CompanyInfo
import io.jhnplotim.mobile.stockmarketapp.domain.model.CompanyListing


fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}


fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name =  name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}