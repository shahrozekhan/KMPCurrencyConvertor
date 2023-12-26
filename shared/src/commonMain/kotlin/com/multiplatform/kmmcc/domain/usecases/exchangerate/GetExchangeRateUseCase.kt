package com.multiplatform.kmmcc.domain.usecases.exchangerate

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.data.sources.RemoteErrorParser
import com.multiplatform.kmmcc.data.sources.local.AppPreferences
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.model.toExchangeRate
import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class GetExchangeRateUseCase (
    private val exchangeRateRepository: ExchangeRateRepository,
    private val appPreferences: AppPreferences,
    private val remoteErrorParser: RemoteErrorParser
) {
    operator fun invoke(): Flow<Resource<List<ExchangeRate>>> = flow {
        emit(Resource.Loading())
        loadFromAsset()
//        when (TimeStampUtils.getTimeStampEnum(appPreferences.timeStamp)) {
//            TimeStampState.TODAY -> {
//                loadFromDatabase()
//            }
//
//            TimeStampState.NOT_TODAY -> {
//                loadFromRemote(onError = {
//                    loadFromDatabase()
//                })
//            }
//
//            TimeStampState.NOT_EXIST -> {
//                loadFromRemote(onError = {
//                    loadFromAsset()
//                })
//            }
//        }
    }

    private suspend fun FlowCollector<Resource<List<ExchangeRate>>>.loadFromAsset() {
        when (val resource = exchangeRateRepository.getExchangeRatesFromAssets()) {
            is Resource.Success -> {
                resource.data?.let { listDataDto ->
                    emit(Resource.Success(listDataDto.map { dataDto -> dataDto.toExchangeRate() }
                        .sortedBy { exchangeRate -> exchangeRate.currency }))
                } ?: run {
                    emit(Resource.Error("No List Available"))
                }
            }

            is Resource.Error -> {
                emit(Resource.Error(resource.message))
            }

            is Resource.Loading -> {}
        }
    }


    private suspend fun FlowCollector<Resource<List<ExchangeRate>>>.loadFromRemote(onError: suspend () -> Unit = {}) {
        when (val remoteResource = exchangeRateRepository.getExchangeRateFromRemote()) {
            is RemoteResource.Success -> {
                emit(Resource.Success(remoteResource.data.map { dataDto -> dataDto.toExchangeRate() }
                    .sortedBy { exchangeRate -> exchangeRate.currency }))
            }

            is RemoteResource.ResourceError -> {
                emit(Resource.Error(remoteErrorParser.parseErrorInfo(remoteResource)))
                onError.invoke()
            }
        }
    }

    private suspend fun FlowCollector<Resource<List<ExchangeRate>>>.loadFromDatabase() {
        val listOfExchangeRates = exchangeRateRepository.getExchangeRatesFromDatabase()
        if (listOfExchangeRates.isNotEmpty()) {
            emit(Resource.Success(
                listOfExchangeRates
                    .map { dataDto -> dataDto.toExchangeRate() }
                    .sortedBy { exchangeRate -> exchangeRate.currency })
            )

        } else {
            emit(Resource.Error("No Currencies found!"))
        }
    }
}