package com.multiplatform.kmmcc.domain.usecases.conversion

import com.multiplatform.kmmcc.common.utils.roundUp
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

/*
    EUR = 1
    PKR = 302.242186 FROM
    INR = 89.252172  TO

    newRate = TO.rate / FROM.rate = 89.252172 / 302.242186 = 0.295300180233609
    convertedValue = newRate * amount

 */
class ConvertExchangeRateUseCase(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        amount: String,
        fromExchangeRate: ExchangeRate,
        toExchangeRateList: List<ExchangeRate>?
    ): List<Pair<ExchangeRate, Double>> = withContext(dispatcher) {
        buildList {
            toExchangeRateList?.forEach { exchangeRate ->
                val newRate = (exchangeRate.rate / fromExchangeRate.rate)

                val convertedExchangeRate =
                    ExchangeRate(exchangeRate.currency, exchangeRate.currencyName, newRate)

                add(
                    convertedExchangeRate to (amount.toDouble() * newRate).roundUp(2)

                )
            }
        }
    }

}
