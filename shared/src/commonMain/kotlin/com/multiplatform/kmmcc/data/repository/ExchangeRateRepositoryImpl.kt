package com.multiplatform.kmmcc.data.repository

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.common.utils.buildExchangeRateDtoListSortedByCurrency
import com.multiplatform.kmmcc.data.dto.ExchangeRateDto
import com.multiplatform.kmmcc.data.dto.ExchangeRateResponseDto
import com.multiplatform.kmmcc.data.sources.local.AppPreferences
import com.multiplatform.kmmcc.data.sources.local.LocalJsonFileReader
import com.multiplatform.kmmcc.data.sources.remote.gateway.ExchangeRateGateway
import com.multiplatform.kmmcc.data.sources.remote.KtorServiceHelper
import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository

//Error Handling in Repository.
class ExchangeRateRepositoryImpl(
    private val exchangeRateGateWay: ExchangeRateGateway,
    private val serviceHelper: KtorServiceHelper,
//    private val exchangeRateDao: ExchangeRateDao,
//    private val defaultDispatcher: CoroutineDispatcher,
//    private val ioDispatcher: CoroutineDispatcher,
    private val fileDataSource: LocalJsonFileReader,
    private val appPreferences: AppPreferences
) : ExchangeRateRepository {

    override suspend fun getExchangeRatesFromAssets(): Resource<List<ExchangeRateDto>> {
        val exchangeRateStr = fileDataSource.loadJsonFile("exchangerate.json")
        val exchangeRate = fileDataSource.loadJsonFileFromResources<ExchangeRateResponseDto>(
            exchangeRateStr
        )
        val symbolStr = fileDataSource.loadJsonFile("symbols.json")
        val symbols = fileDataSource.loadJsonFileFromResources<ExchangeRateResponseDto>(
            symbolStr
        )
        return if (exchangeRate is Resource.Success && symbols is Resource.Success) {
            val listOfExchangeRate =
                buildExchangeRateDtoListSortedByCurrency(
                    exchangeRate.data?.rates,
                    symbols.data?.symbols
                )
//            insertExchangeRatesToDatabase(listOfExchangeRate)
            appPreferences.timeStamp = exchangeRate.data?.timestamp.toString()
            Resource.Success(listOfExchangeRate)
        } else {
            if (exchangeRate is Resource.Error)
                Resource.Error(exchangeRate.message)
            else {
                Resource.Error(symbols.message)
            }
        }

    }

    override suspend fun getExchangeRateFromRemote(): RemoteResource<List<ExchangeRateDto>> {
        val exchangeRateRemoteResource =
            serviceHelper.call { exchangeRateGateWay.getExchangeRate() }
        val symbolsRemoteResource = serviceHelper.call { exchangeRateGateWay.getSymbols() }
        when (exchangeRateRemoteResource) {
            is RemoteResource.Success -> {
                val listOfExchangeRate: List<ExchangeRateDto> = when (symbolsRemoteResource) {
                    is RemoteResource.Success -> {
                        buildExchangeRateDtoListSortedByCurrency(
                            exchangeRateRemoteResource.data.rates,
                            symbolsRemoteResource.data.symbols
                        )
                    }

                    is RemoteResource.ResourceError -> {
                        buildExchangeRateDtoListSortedByCurrency(
                            exchangeRateRemoteResource.data.rates
                        )
                    }
                }
//                insertExchangeRatesToDatabase(listOfExchangeRate)
                appPreferences.timeStamp =
                    exchangeRateRemoteResource.data.timestamp.toString()
                return RemoteResource.Success(listOfExchangeRate)
            }

            is RemoteResource.ResourceError -> {
                return exchangeRateRemoteResource
            }
        }
    }

    override suspend fun insertExchangeRatesToDatabase(listOfExchangeRate: List<ExchangeRateDto>) {
    }

    override suspend fun getExchangeRatesFromDatabase(): List<ExchangeRateDto> {
        return listOf()
    }

    override fun SaveFromExchangeRate(exchangeRateStr: String) {
        appPreferences.baseCurrency = exchangeRateStr
    }

}