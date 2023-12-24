package com.multiplatform.kmmcc.domain.usecases.exchangerate

import com.multiplatform.kmmcc.common.base.RemoteResource
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.data.sources.RemoteErrorParser
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.model.toExchangeRate
import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForceSyncExchangeRatesUseCase(
    private val exchangeRateRepository: ExchangeRateRepository,
    private val remoteErrorParser: RemoteErrorParser
) {
    operator fun invoke(): Flow<Resource<List<ExchangeRate>>> = flow {
        emit(Resource.Loading())
        when (val remoteResource = exchangeRateRepository.getExchangeRateFromRemote()) {
            is RemoteResource.Success -> {
                Resource.Success(remoteResource.data.map { dataDto -> dataDto.toExchangeRate() }
                    .sortedBy { it.currency })
            }

            is RemoteResource.ResourceError -> {
                Resource.Error(remoteErrorParser.parseErrorInfo(remoteResource))
            }
        }
    }

}