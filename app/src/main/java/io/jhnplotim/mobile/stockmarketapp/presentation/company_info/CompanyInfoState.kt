package io.jhnplotim.mobile.stockmarketapp.presentation.company_info

import io.jhnplotim.mobile.stockmarketapp.domain.model.CompanyInfo
import io.jhnplotim.mobile.stockmarketapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
