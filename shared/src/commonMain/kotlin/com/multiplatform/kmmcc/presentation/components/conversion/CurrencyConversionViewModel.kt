package com.multiplatform.kmmcc.presentation.components.conversion

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.domain.model.appDefaultExchangeRate
import com.multiplatform.kmmcc.domain.usecases.conversion.ConvertExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.ForceSyncExchangeRatesUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.GetExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.SaveFromExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.favorite.GetFavoriteExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.favorite.MarkExchangeRateToFavoriteUseCase
import com.multiplatform.kmmcc.presentation.CommonScreenEvent
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CurrencyConversionViewModel(
    private val markExchangeRateToFavoriteUseCase: MarkExchangeRateToFavoriteUseCase,
    private val getFavoriteExchangeRateUseCase: GetFavoriteExchangeRateUseCase,
    private val convertExchangeRateUseCase: ConvertExchangeRateUseCase,
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val forceSyncExchangeRatesUseCase: ForceSyncExchangeRatesUseCase,
    private val saveSelectedExchangeRateUseCase: SaveFromExchangeRateUseCase
) : ViewModel() {

    init {
//        loadFavoriteExchangeRate()
        loadExchangeRate()
    }

    private val _mutableExchangeRateViewState = mutableStateOf(CurrencyConversionScreenState())
    val exchangeRateViewState: State<CurrencyConversionScreenState> = _mutableExchangeRateViewState

    private val _eventFlow = MutableSharedFlow<CommonScreenEvent>()
    val commonEventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: CurrencyConversionScreenEvent) {
        when (event) {
            is CurrencyConversionScreenEvent.EnteredAmount -> {
                _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                    amount = event.value
                )
            }

            is CurrencyConversionScreenEvent.SelectFromCurrency -> {
                val selectedCurrency =
                    exchangeRateViewState.value.listOfCurrency?.find { event.value == it.currency }
                        ?: ExchangeRate.appDefaultExchangeRate
                _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                    fromCurrency = selectedCurrency
                )
            }

            is CurrencyConversionScreenEvent.MarkToFavoriteCurrency -> {
                viewModelScope.launch {
                    val newFavoritesList = markExchangeRateToFavoriteUseCase(
                        event.value, exchangeRateViewState.value.favoriteCurrencies
                    )
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        favoriteCurrencies = newFavoritesList
                    )
                }
            }

            is CurrencyConversionScreenEvent.RemoveCurrencyFromSelected -> {
                viewModelScope.launch {
                    val newFavoritesList = markExchangeRateToFavoriteUseCase(
                        event.value, exchangeRateViewState.value.favoriteCurrencies, false
                    )
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        favoriteCurrencies = newFavoritesList
                    )
                }
            }

            is CurrencyConversionScreenEvent.ForceSyncCurrencyConversion -> {
                forceSyncExchangeRatesUseCase().onEach {
                    when (it) {
                        is Resource.Success -> {
                            _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                                isForceSyncingExchangeRate = false,
                                listOfCurrency = it.data,
                                errorMessage = ""
                            )
                            loadFavoriteExchangeRate(onCompleteLoading = {
                                if (!exchangeRateViewState.value.favoriteCurrencies.isNullOrEmpty()) onEvent(
                                    CurrencyConversionScreenEvent.ConvertCurrencyConversion
                                )
                            })
                        }

                        is Resource.Error -> {
                            showSnackBarError(
                                exchangeRateViewState.value.errorMessage,
                                SnackbarDuration.Indefinite
                            )
                            _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                                isForceSyncingExchangeRate = false
                            )
                        }

                        is Resource.Loading -> {
                            _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                                isForceSyncingExchangeRate = true
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }

            is CurrencyConversionScreenEvent.ConvertCurrencyConversion -> {
                viewModelScope.launch {
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isConverting = true
                    )
                    saveSelectedExchangeRateUseCase(exchangeRateViewState.value.fromCurrency.currency)
                    exchangeRateViewState.value.apply {
                        val listOfConvertedExchangeRate = convertExchangeRateUseCase(
                            amount = amount,
                            fromExchangeRate = fromCurrency,
                            toExchangeRateList = favoriteCurrencies
                        )
                        _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                            listOfConvertedAgainstBase = listOfConvertedExchangeRate,
                            isConverting = false,
                            convertedCurrency = exchangeRateViewState.value.fromCurrency
                        )

                    }
                }
            }
        }
    }

    private suspend fun showSnackBarError(
        message: String, duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        _eventFlow.emit(
            CommonScreenEvent.ShowSnackbar(
                message = message, duration = duration
            )
        )
    }

    private fun loadExchangeRate() {
        getExchangeRateUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isCurrenciesLoading = false, listOfCurrency = it.data, errorMessage = ""
                    )
                }

                is Resource.Error -> {
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isCurrenciesLoading = false, errorMessage = it.message
                    )
                    showSnackBarError(exchangeRateViewState.value.errorMessage)

                }

                is Resource.Loading -> {
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isCurrenciesLoading = true, errorMessage = ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadFavoriteExchangeRate(onCompleteLoading: () -> Unit = {}) {
        getFavoriteExchangeRateUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isFavoriteLoading = false, favoriteCurrencies = it.data
                    )
                    onCompleteLoading.invoke()
                }

                is Resource.Loading -> {
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isFavoriteLoading = true
                    )
                }

                is Resource.Error -> {
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isFavoriteLoading = false
                    )
                    showSnackBarError(it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

}