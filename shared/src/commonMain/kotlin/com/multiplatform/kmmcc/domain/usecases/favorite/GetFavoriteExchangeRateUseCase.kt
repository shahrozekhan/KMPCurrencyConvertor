package com.multiplatform.kmmcc.domain.usecases.favorite

import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
//import com.shahroze.currencyconvertorandroid.domain.model.toExchangeRate
//import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//import javax.inject.Inject

class GetFavoriteExchangeRateUseCase(
    private val favoriteExchangeRateRepository: FavoriteExchangeRateRepository
) {
    operator fun invoke(): Flow<Resource<List<ExchangeRate>>> = flow {
        emit(Resource.Loading<List<ExchangeRate>>())
//        val listOfFavoriteExchangeRates = favoriteExchangeRateRepository.getFavoriteExchangeRates()
//        if (listOfFavoriteExchangeRates.isEmpty()) {
//            emit(Resource.Error("No favorites found! Please select currencies to mark favorite."))
//        } else {
//            emit(Resource.Success(listOfFavoriteExchangeRates.map { dataDto ->
//                dataDto.toExchangeRate()
//            }))
//        }
    }.flowOn(Dispatchers.IO)
}