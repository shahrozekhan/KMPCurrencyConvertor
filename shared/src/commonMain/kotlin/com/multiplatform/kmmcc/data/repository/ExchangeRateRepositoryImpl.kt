package com.multiplatform.kmmcc.data.repository

import com.multiplatform.kmmcc.common.Constants
import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY
import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.common.utils.buildExchangeRateDtoListSortedByCurrency
import com.multiplatform.kmmcc.data.dto.ExchangeRateDto
import com.multiplatform.kmmcc.data.dto.ExchangeRateResponseDto
import com.multiplatform.kmmcc.data.sources.local.preferences.AppPreferences
import com.multiplatform.kmmcc.data.sources.local.filereader.LocalJsonFileDataSource
import com.multiplatform.kmmcc.data.sources.remote.gateway.ExchangeRateDataSource
import com.multiplatform.kmmcc.database.ApplicationDB
import com.multiplatform.kmmcc.domain.model.toExchangeRateEntity
import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository
import database.ExchangeRateEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

//Error Handling in Repository.
class ExchangeRateRepositoryImpl(
    private val exchangeRateDataSource: ExchangeRateDataSource,
    private val exchangeRateDb: ApplicationDB,
    private val defaultDispatcher: CoroutineDispatcher,
    private val fileDataSource: LocalJsonFileDataSource,
    private val appPreferences: AppPreferences
) : ExchangeRateRepository {

    override suspend fun getExchangeRatesFromAssets(): Resource<List<ExchangeRateDto>> {
        val exchangeRateStr = fileDataSource.loadJsonFile("files/exchangerate.json")
        val exchangeRate = fileDataSource.loadJsonFileFromResources<ExchangeRateResponseDto>(
            exchangeRateStr
        )
        val symbolStr = fileDataSource.loadJsonFile("files/symbols.json")
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
//            appPreferences.timeStamp = exchangeRate.data?.timestamp.toString()
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
        val exchangeRateRemoteResource = exchangeRateDataSource.getExchangeRate()
        val symbolsRemoteResource = exchangeRateDataSource.getSymbols()
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
                insertExchangeRatesToDatabase(listOfExchangeRate)
                appPreferences.timeStamp =
                    exchangeRateRemoteResource.data.timestamp.toString()
                return RemoteResource.Success(listOfExchangeRate)
            }

            is RemoteResource.ResourceError -> {
                return exchangeRateRemoteResource
            }
        }
    }

    override suspend fun insertExchangeRatesToDatabase(listOfExchangeRate: List<ExchangeRateDto>) =
        withContext(defaultDispatcher) {
            listOfExchangeRate.forEach {
                exchangeRateDb.exchangerateQueries.updateAndInsertExchangeRate(it.toExchangeRateEntity())
            }
        }

    override suspend fun getExchangeRatesFromDatabase(): List<ExchangeRateEntity> =
        withContext(defaultDispatcher) {
            exchangeRateDb.exchangerateQueries.getAllExchangeRates().executeAsList()
        }

    override fun saveExchangeRatePreferences(key: String, exchangeRateStr: String) {
        appPreferences.instance.put(key, exchangeRateStr)
    }

    override fun getExchangeRatePreferences(key: String) {
        appPreferences.instance.getString(key, DEFAULT_CURRENCY)
    }


}