package com.multiplatform.kmmcc.domain.usecases.favorite

import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetToFavoriteExchangeRateUseCase(
    private val favoriteExchangeRateRepository: FavoriteExchangeRateRepository
) {
    operator fun invoke() = flow {
        emit(Resource.Loading())
        val listOfToFavoriteExchangeRates =
            favoriteExchangeRateRepository.getToExchangeRates()
        val listOfFromFavoriteExchangeRates =
            favoriteExchangeRateRepository.getFromExchangeRates()
        if (listOfToFavoriteExchangeRates.isEmpty() && listOfFromFavoriteExchangeRates.isEmpty()) {
            emit(Resource.Error("No favorites found! Please select currencies to mark favorite."))
        } else {
            emit(Resource.Success(listOfToFavoriteExchangeRates to listOfFromFavoriteExchangeRates))
        }
    }.flowOn(Dispatchers.IO)
}