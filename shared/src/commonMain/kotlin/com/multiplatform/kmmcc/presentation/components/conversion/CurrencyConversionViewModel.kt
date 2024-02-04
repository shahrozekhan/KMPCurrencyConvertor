package com.multiplatform.kmmcc.presentation.components.conversion

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.multiplatform.kmmcc.common.base.Resource
import com.multiplatform.kmmcc.domain.usecases.conversion.ConvertListOfExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.GetExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.favorite.GetToFavoriteExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.favorite.MarkExchangeRateToFavoriteUseCase
import com.multiplatform.kmmcc.presentation.CommonScreenEvent
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CurrencyConversionViewModel(
    private val markExchangeRateToFavoriteUseCase: MarkExchangeRateToFavoriteUseCase,
    private val getToFavoriteExchangeRateUseCase: GetToFavoriteExchangeRateUseCase,
    private val convertExchangeRateUseCase: ConvertListOfExchangeRateUseCase,
    private val getExchangeRateUseCase: GetExchangeRateUseCase
) : ViewModel() {

    init {
        loadFavoriteExchangeRate()
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
                exchangeRateViewState.value.apply {
                    if (!fromFavorites.contains(event.value)) {
                        val newFavList = fromFavorites.toMutableList()
                        newFavList.add(event.value)
                        _mutableExchangeRateViewState.value = copy(
                            fromFavorites = newFavList
                        )
                    }
                }
            }

            is CurrencyConversionScreenEvent.SelectToCurrency -> {
                exchangeRateViewState.value.apply {
                    if (!toFavorites.contains(event.value)) {
                        val newFavList = toFavorites.toMutableList()
                        newFavList.add(event.value)
                        _mutableExchangeRateViewState.value = copy(
                            toFavorites = newFavList
                        )
                    }
                }
            }

            is CurrencyConversionScreenEvent.RemoveCurrencyFromExchangeRates -> {
                viewModelScope.launch {
                    val newFavoritesList = exchangeRateViewState.value.fromFavorites.toMutableList()
                    newFavoritesList.removeAt(event.index)
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        fromFavorites = newFavoritesList
                    )
                }
            }

            is CurrencyConversionScreenEvent.RemoveCurrencyToExchangeRates -> {
                viewModelScope.launch {
                    val newFavoritesList = exchangeRateViewState.value.toFavorites.toMutableList()
                    newFavoritesList.removeAt(event.index)
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        toFavorites = newFavoritesList
                    )
                }
            }

            is CurrencyConversionScreenEvent.ForceSyncCurrencyConversion -> {
//                forceSyncExchangeRatesUseCase().onEach {
//                    when (it) {
//                        is Resource.Success -> {
//                            _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
//                                isForceSyncingExchangeRate = false,
//                                listOfCurrency = it.data,
//                                errorMessage = ""
//                            )
//                            loadTOExchangeRate(onCompleteLoading = {
//                                if (!exchangeRateViewState.value.favoriteCurrencies.isNullOrEmpty()) onEvent(
//                                    CurrencyConversionScreenEvent.ConvertCurrencyConversion
//                                )
//                            })
//                        }
//
//                        is Resource.Error -> {
//                            showSnackBarError(
//                                exchangeRateViewState.value.errorMessage,
//                                SnackbarDuration.Indefinite
//                            )
//                            _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
//                                isForceSyncingExchangeRate = false
//                            )
//                        }
//
//                        is Resource.Loading -> {
//                            _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
//                                isForceSyncingExchangeRate = true
//                            )
//                        }
//                    }
//                }.launchIn(viewModelScope)
            }

            is CurrencyConversionScreenEvent.ConvertCurrencyConversion -> {
                viewModelScope.launch {
                    markExchangeRateToFavoriteUseCase.invoke(
                        exchangeRateViewState.value.toFavorites,
                        exchangeRateViewState.value.fromFavorites
                    )
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isConverting = true
                    )
                    exchangeRateViewState.value.apply {
                        val listOfConvertedExchangeRate = convertExchangeRateUseCase(
                            amount = amount,
                            fromExchangeRates = fromFavorites,
                            toExchangeRates = toFavorites
                        )
                        _mutableExchangeRateViewState.value = copy(
                            convertedExchangeRate = listOfConvertedExchangeRate,
                            isConverting = false
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
        getToFavoriteExchangeRateUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _mutableExchangeRateViewState.value = exchangeRateViewState.value.copy(
                        isFavoriteLoading = false,
                        toFavorites = it.data!!.first,
                        fromFavorites = it.data.second,
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