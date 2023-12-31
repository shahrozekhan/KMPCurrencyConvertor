package com.multiplatform.kmmcc.domain.usecases.favorite

import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetFavoriteExchangeRateUseCase(
    private val favoriteExchangeRateRepository: FavoriteExchangeRateRepository
) {
    operator fun invoke() = flow {
        emit(Resource.Loading())
        val listOfFavoriteExchangeRates =
            favoriteExchangeRateRepository.getFavoriteExchangeRates()
        if (listOfFavoriteExchangeRates.isEmpty()) {
            emit(Resource.Error("No favorites found! Please select currencies to mark favorite."))
        } else {
            emit(Resource.Success(listOfFavoriteExchangeRates))
        }
    }.flowOn(Dispatchers.IO)
}