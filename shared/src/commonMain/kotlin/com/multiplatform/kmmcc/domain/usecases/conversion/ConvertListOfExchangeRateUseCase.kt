package com.multiplatform.kmmcc.domain.usecases.conversion

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ConvertListOfExchangeRateUseCase(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        amount: String,
        fromExchangeRates: List<ExchangeRate>?,
        toExchangeRates: List<ExchangeRate>?
    ): List<Pair<ExchangeRate, List<Pair<ExchangeRate, BigDecimal>>>> = withContext(dispatcher) {
        buildList {
            fromExchangeRates?.forEach { fromExchangeRate ->
                val convertedList = buildList<Pair<ExchangeRate, BigDecimal>> {
                    toExchangeRates?.forEach { exchangeRate ->
                        val newRate = (exchangeRate.rate / fromExchangeRate.rate)

                        val convertedExchangeRate =
                            ExchangeRate(
                                exchangeRate.currency, exchangeRate.currencyName,
                                newRate.toBigDecimal(
                                    decimalMode = DecimalMode(
                                        10, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO, 6
                                    )
                                ).doubleValue(false)
                            )
                        add(
                            convertedExchangeRate to amount.toBigDecimal().multiply(
                                newRate.toBigDecimal(
                                    decimalMode = DecimalMode(
                                        6, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO, 6
                                    )
                                ),
                                decimalMode = DecimalMode(
                                    30,
                                    RoundingMode.ROUND_HALF_AWAY_FROM_ZERO,
                                    6
                                )
                            )

                        )
                    }
                }
                add(
                    fromExchangeRate
                            to convertedList
                )
            }
        }
    }
}