package com.multiplatform.kmmcc.domain.usecases.conversion

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import kotlinx.coroutines.CoroutineDispatcher
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
        amount: String, fromExchangeRate: ExchangeRate, toExchangeRateList: List<ExchangeRate>?
    ): List<Pair<ExchangeRate, BigDecimal>> = withContext(dispatcher) {
        buildList {
            toExchangeRateList?.forEach { exchangeRate ->
                val newRate = (exchangeRate.rate / fromExchangeRate.rate)

                val convertedExchangeRate =
                    ExchangeRate(exchangeRate.currency, exchangeRate.currencyName,
                        newRate.toBigDecimal(
                            decimalMode = DecimalMode(
                                10, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO, 6
                            )
                        ).doubleValue(false))
                add(
                    convertedExchangeRate to amount.toBigDecimal().multiply(
                        newRate.toBigDecimal(
                            decimalMode = DecimalMode(
                                6, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO, 6
                            )
                        ),
                        decimalMode = DecimalMode(30, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO, 6)
                    )

                )
            }
        }
    }

}
