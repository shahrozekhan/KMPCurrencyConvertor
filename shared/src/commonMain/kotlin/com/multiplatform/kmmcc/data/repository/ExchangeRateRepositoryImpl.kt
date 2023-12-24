package com.multiplatform.kmmcc.data.repository

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.common.utils.buildExchangeRateDtoListSortedByCurrency
import com.multiplatform.kmmcc.data.dto.ExchangeRateDto
import com.multiplatform.kmmcc.data.gateway.ExchangeRateGateway
import com.multiplatform.kmmcc.data.sources.KtorServiceHelper
import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

//Error Handling in Repository.
class ExchangeRateRepositoryImpl(
    private val exchangeRateGateWay: ExchangeRateGateway,
    private val serviceHelper: KtorServiceHelper,
//    private val exchangeRateDao: ExchangeRateDao,
//    private val defaultDispatcher: CoroutineDispatcher,
//    private val ioDispatcher: CoroutineDispatcher,
//    private val fileDataSource: AssetFileHelper,
//    private val appPreferences: AppPreferences
) : ExchangeRateRepository {
    //    override fun getFlowExchangeRateFromRemote() =
//        getExchangeRate()
//            .zip(getSymbols()) { exchangeRateRemoteResource, symbolsRemoteResource ->
//                when (exchangeRateRemoteResource) {
//                    is RemoteResource.Success -> {
//                        val listOfExchangeRate: List<ExchangeRateDto> =
//                            when (symbolsRemoteResource) {
//                                is RemoteResource.Success -> {
//                                    buildExchangeRateDtoListSortedByCurrency(
//                                        exchangeRateRemoteResource.data.rates,
//                                        symbolsRemoteResource.data.symbols
//                                    )
//                                }
//
//                                is RemoteResource.ResourceError -> {
//                                    buildExchangeRateDtoListSortedByCurrency(
//                                        exchangeRateRemoteResource.data.rates
//                                    )
//                                }
//                            }
//                        insertExchangeRatesToDatabase(listOfExchangeRate)
////                        appPreferences.timeStamp =
////                            exchangeRateRemoteResource.data.timestamp.toString()
//                        RemoteResource.Success(listOfExchangeRate)
//                    }
//
//                    is RemoteResource.ResourceError -> {
//                        exchangeRateRemoteResource
//                    }
//                }
//
//            }
//
//    override suspend fun getExchangeRateFromRemote(): RemoteResource<List<ExchangeRateDto>> {
//        val exchangeRateRemoteResource =
//            serviceHelper.call { exchangeRateGateWay.getExchangeRate() }
//        val symbolsRemoteResource = serviceHelper.call { exchangeRateGateWay.getSymbols() }
//        when (exchangeRateRemoteResource) {
//            is RemoteResource.Success -> {
//                val listOfExchangeRate: List<ExchangeRateDto> =
//                    when (symbolsRemoteResource) {
//                        is RemoteResource.Success -> {
//                            buildExchangeRateDtoListSortedByCurrency(
//                                exchangeRateRemoteResource.data.rates,
//                                symbolsRemoteResource.data.symbols
//                            )
//                        }
//
//                        is RemoteResource.ResourceError -> {
//                            buildExchangeRateDtoListSortedByCurrency(
//                                exchangeRateRemoteResource.data.rates
//                            )
//                        }
//                    }
//                insertExchangeRatesToDatabase(listOfExchangeRate)
//                appPreferences.timeStamp =
//                    exchangeRateRemoteResource.data.timestamp.toString()
//                return RemoteResource.Success(listOfExchangeRate)
//            }
//
//            is RemoteResource.ResourceError -> {
//                return exchangeRateRemoteResource
//            }
//        }
//    }
//
//    override suspend fun getExchangeRatesFromAssets(): Resource<List<ExchangeRateDto>> {
//        val exchangeRate = fileDataSource.loadJsonFromAssets<ExchangeRateResponseDto>(
//            "exchangerate.json",
//        )
//        val symbols = fileDataSource.loadJsonFromAssets<ExchangeRateResponseDto>(
//            "symbols.json",
//        )
//        return if (exchangeRate is Resource.Success && symbols is Resource.Success) {
//            val listOfExchangeRate =
//                buildExchangeRateDtoListSortedByCurrency(
//                    exchangeRate.data?.rates,
//                    symbols.data?.symbols
//                )
//            insertExchangeRatesToDatabase(listOfExchangeRate)
//            appPreferences.timeStamp = exchangeRate.data?.timestamp.toString()
//            Resource.Success(listOfExchangeRate)
//        } else {
//            if (exchangeRate is Resource.Error)
//                Resource.Error(exchangeRate.message)
//            else {
//                Resource.Error(symbols.message)
//            }
//        }
//
//    }
//
//    private fun getExchangeRate() =
//        flow {
//            emit(serviceHelper.call { exchangeRateGateWay.getExchangeRate() })
//        }.flowOn(ioDispatcher)
//
//    private fun getSymbols() =
//        flow {
//            emit(serviceHelper.call { exchangeRateGateWay.getSymbols() })
//        }.flowOn(ioDispatcher)
//
//    override suspend fun insertExchangeRatesToDatabase(listOfExchangeRate: List<ExchangeRateDto>) =
//        withContext(defaultDispatcher) {
//            exchangeRateDao.getFavoriteExchangeRates().forEach { favorite ->
//                listOfExchangeRate.find { favorite.currency == it.currency }?.selected =
//                    favorite.selected
//            }
//            exchangeRateDao.insertAll(listOfExchangeRate)
//            Unit
//        }
//
//    override suspend fun getExchangeRatesFromDatabase(): List<ExchangeRateDto> =
//        withContext(defaultDispatcher) {
//            exchangeRateDao.getExchangeRates()
//        }
//
//    override fun getFlowExchangeRatesFromAssets(): Flow<Resource<List<ExchangeRateDto>>> {
//        return fileDataSource.loadFlowJsonFromAssets<ExchangeRateResponseDto>(
//            "exchangerate.json",
//        ).zip(
//            fileDataSource.loadFlowJsonFromAssets<ExchangeRateResponseDto>(
//                "symbols.json",
//            )
//        ) { exchangeRate, symbols ->
//            if (exchangeRate is Resource.Loading && symbols is Resource.Loading) {
//                Resource.Loading()
//            } else if (exchangeRate is Resource.Success && symbols is Resource.Success) {
//                val listOfExchangeRate =
//                    buildExchangeRateDtoListSortedByCurrency(
//                        exchangeRate.data?.rates,
//                        symbols.data?.symbols
//                    )
//                insertExchangeRatesToDatabase(listOfExchangeRate)
//                appPreferences.timeStamp = exchangeRate.data?.timestamp.toString()
//                Resource.Success(listOfExchangeRate)
//            } else {
//                if (exchangeRate is Resource.Error)
//                    Resource.Error(exchangeRate.message)
//                else {
//                    Resource.Error(symbols.message)
//                }
//            }
//        }
//    }

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
//                KMMPreferences(context = KMMContext()).put(
//                    PreferenceKeys.TIME_STAMP, exchangeRateRemoteResource.data.timestamp.toString()
//                )
                return RemoteResource.Success(listOfExchangeRate)
            }

            is RemoteResource.ResourceError -> {
                return exchangeRateRemoteResource
            }
        }
    }

    override suspend fun insertExchangeRatesToDatabase(listOfExchangeRate: List<ExchangeRateDto>) {
        TODO("Not yet implemented")
    }

    override suspend fun getExchangeRatesFromDatabase(): List<ExchangeRateDto> {
        TODO("Not yet implemented")
    }


    override suspend fun getExchangeRatesFromAssets(): Resource<List<ExchangeRateDto>> {
        TODO("Not yet implemented")
    }
}