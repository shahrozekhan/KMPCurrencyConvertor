package com.multiplatform.kmmcc.presentation.components.conversion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import com.multiplatform.kmmcc.common.enums.CompactWindow
import com.multiplatform.kmmcc.common.enums.MediumWindow
import com.multiplatform.kmmcc.common.enums.WindowInfo
import com.multiplatform.kmmcc.common.utils.containsDigitsAndDecimalOnly
import com.multiplatform.kmmcc.common.utils.empty
import com.multiplatform.kmmcc.domain.model.ExchangeRate
import com.multiplatform.kmmcc.presentation.CommonScreenEvent
import com.multiplatform.kmmcc.presentation.views.Body1Normal
import com.multiplatform.kmmcc.presentation.views.Body2Medium
import com.multiplatform.kmmcc.presentation.views.Body2Normal
import com.multiplatform.kmmcc.presentation.views.ComposeButton
import com.multiplatform.kmmcc.presentation.views.ComposeIcon
import com.multiplatform.kmmcc.presentation.views.HeadingMedium
import com.multiplatform.kmmcc.presentation.views.TitleLargeMedium
import com.multiplatform.kmmcc.presentation.views.VerticalDivider
import kmmcc.shared.generated.resources.Res
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@ExperimentalMaterial3Api
@Composable
fun CurrencyConversionScreen(windowInfo: MutableState<WindowInfo>) {
    val viewModel: CurrencyConversionViewModel = koinInject<CurrencyConversionViewModel>()
    val currencyRateState = viewModel.exchangeRateViewState.value
    val snackBarHostState = remember { SnackbarHostState() }
    val localCoroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.commonEventFlow.collect {
            when (it) {
                is CommonScreenEvent.ShowSnackbar -> {
                    localCoroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = it.message,
                            withDismissAction = true,
                            duration = it.duration
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    )
                    .padding(bottom = 8.dp)
                    .fillMaxSize(),
                state = rememberLazyListState()
            ) {
                stickyHeader {
                    HeadingMedium(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 16.dp),
                        text = stringResource(Res.string.app_name),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                item {
                    if (!currencyRateState.isCurrenciesLoading && !currencyRateState.listOfCurrency.isNullOrEmpty()) {
                        when (windowInfo.value) {
                            is CompactWindow, is MediumWindow -> {
                                CompactDropDownView(currencyRateState, viewModel, windowInfo)
                            }

                            else -> {
                                ExpandedDropDownView(currencyRateState, viewModel, windowInfo)
                            }
                        }
                    }
                }
                stickyHeader {
                    Column(
                        modifier = Modifier.background(MaterialTheme.colorScheme.background),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        VerticalDivider(dp = 8.dp)
                        OutlinedTextField(
                            value = currencyRateState.amount,
                            onValueChange = {
                                if (it.containsDigitsAndDecimalOnly() || it.isEmpty()) {
                                    viewModel.onEvent(CurrencyConversionScreenEvent.EnteredAmount(it))
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = {
                                Body1Normal("100.00")
                            },
                            label = {
                                Body2Medium(
                                    "${stringResource(Res.string.tv_amount)})",
                                    textAlign = TextAlign.Center
                                )
                            },
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        VerticalDivider(dp = 12.dp)

                        ComposeButton(
                            modifier = Modifier
                                .fillMaxWidth(1f),
                            text = stringResource(Res.string.tv_convert),
                            enabled = !currencyRateState.isCurrenciesLoading &&
                                    !currencyRateState.isFavoriteLoading &&
                                    !currencyRateState.isConverting &&
                                    !currencyRateState.isForceSyncingExchangeRate &&
                                    currencyRateState.amount.isNotEmpty(),
                            onClick = {
                                viewModel.onEvent(CurrencyConversionScreenEvent.ConvertCurrencyConversion)
                            }
                        )
                        VerticalDivider(dp = 12.dp)

                    }
                }
                if (currencyRateState.convertedExchangeRate.isNotEmpty()) {
                    items(
                        items = currencyRateState.convertedExchangeRate
                    ) { pair: Pair<ExchangeRate, List<Pair<ExchangeRate, BigDecimal>>> ->
                        CurrencyContainer(pair)

                    }
                }

            }

            if (currencyRateState.isCurrenciesLoading ||
                currencyRateState.isFavoriteLoading ||
                currencyRateState.isConverting ||
                currencyRateState.isForceSyncingExchangeRate
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(64.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 4.dp,
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CurrencyContainer(
    pair: Pair<ExchangeRate, List<Pair<ExchangeRate, BigDecimal>>>
) {
    Column {
        Column(
            Modifier.background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalDivider(dp = 12.dp)
            TitleLargeMedium(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                text = pair.first.currency,
                textAlign = TextAlign.Center
            )
            VerticalDivider(dp = 8.dp)
            Divider(
                modifier = Modifier.fillMaxWidth().height(1.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
            VerticalDivider(dp = 8.dp)
            pair.second.forEach {
                CurrencyItem(it, pair.first)
                VerticalDivider(dp = 8.dp)
            }
        }
        VerticalDivider(dp = 8.dp)
    }

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ExpandedDropDownView(
    currencyRateState: CurrencyConversionScreenState,
    viewModel: CurrencyConversionViewModel,
    windowInfo: MutableState<WindowInfo>
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column {
            VerticalDivider(dp = 8.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val currencyName = stringResource(Res.string.tv_select_currency)
                CurrencyExposedDropdownMenuBox(
                    placeHolderText = stringResource(Res.string.tv_from),
                    modifier = Modifier.weight(.5f),
                    defaultText = currencyName,
                    currencyRateState = currencyRateState
                ) { selectedCurrency ->
                    viewModel.onEvent(
                        CurrencyConversionScreenEvent.SelectFromCurrency(
                            selectedCurrency
                        )
                    )
                }

                val textState = stringResource(Res.string.tv_select_currency)
                CurrencyExposedDropdownMenuBox(
                    placeHolderText = stringResource(Res.string.tv_to),
                    defaultText = textState,
                    modifier = Modifier.weight(.5f),
                    currencyRateState = currencyRateState
                ) { selectedCurrency ->
                    viewModel.onEvent(
                        CurrencyConversionScreenEvent.SelectToCurrency(
                            selectedCurrency
                        )
                    )
                }
            }
        }

        Column {
            VerticalDivider(dp = 8.dp)
            Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){

                FlowRow(
                    Modifier.width(((windowInfo.value.windowWidth / 2)-20).dp),
                    currencyRateState.fromFavorites,
                ) { index, exchangeRate ->
                    viewModel.onEvent(
                        CurrencyConversionScreenEvent.RemoveCurrencyFromExchangeRates(
                            index = index,
                            value = exchangeRate
                        )
                    )
                }

                FlowRow(
                    Modifier.width(((windowInfo.value.windowWidth / 2)-16).dp),
                    currencyRateState.toFavorites,
                ) { index, exchangeRate ->
                    viewModel.onEvent(
                        CurrencyConversionScreenEvent.RemoveCurrencyToExchangeRates(
                            index = index,
                            value = exchangeRate
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun CompactDropDownView(
    currencyRateState: CurrencyConversionScreenState,
    viewModel: CurrencyConversionViewModel,
    windowInfo: MutableState<WindowInfo>
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column {
            VerticalDivider(dp = 8.dp)
            val currencyName = stringResource(Res.string.tv_select_currency)
            CurrencyExposedDropdownMenuBox(
                placeHolderText = stringResource(Res.string.tv_from),
                modifier = Modifier,
                defaultText = currencyName,
                currencyRateState = currencyRateState
            ) { selectedCurrency ->
                viewModel.onEvent(
                    CurrencyConversionScreenEvent.SelectFromCurrency(
                        selectedCurrency
                    )
                )
            }
            VerticalDivider(dp = 8.dp)
            FlowRow(
                Modifier.fillMaxWidth(),
                currencyRateState.fromFavorites
            ) { index, exchangeRate ->
                viewModel.onEvent(
                    CurrencyConversionScreenEvent.RemoveCurrencyFromExchangeRates(
                        index = index,
                        value = exchangeRate
                    )
                )
            }
        }

        Column {
            VerticalDivider(dp = 8.dp)
            val textState = stringResource(Res.string.tv_select_currency)
            CurrencyExposedDropdownMenuBox(
                placeHolderText = stringResource(Res.string.tv_to),
                defaultText = textState,
                currencyRateState = currencyRateState
            ) { selectedCurrency ->
                viewModel.onEvent(
                    CurrencyConversionScreenEvent.SelectToCurrency(
                        selectedCurrency
                    )
                )
            }
            VerticalDivider(dp = 8.dp)
            FlowRow(
                Modifier.fillMaxWidth(),
                currencyRateState.toFavorites
            ) { index, exchangeRate ->
                viewModel.onEvent(
                    CurrencyConversionScreenEvent.RemoveCurrencyToExchangeRates(
                        index = index,
                        value = exchangeRate
                    )
                )
            }
            VerticalDivider(dp = 8.dp)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalResourceApi::class)
@Composable
fun CurrencyItem(
    pair: Pair<ExchangeRate, BigDecimal>,
    fromExchangeRate: ExchangeRate
) {
    val exchangeRate = pair.first
    val convertedAmount = pair.second
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Body2Medium(text = exchangeRate.currency, textAlign = TextAlign.Center)
            if (exchangeRate.currencyName.isNotEmpty())
                Body2Normal(
                    text = " (${exchangeRate.currencyName})",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Body1Normal(text = "1 ${fromExchangeRate.currency}")
            Body2Normal(
                text = exchangeRate.rate.toBigDecimal()
                    .toPlainString() + " ${exchangeRate.currency}"
            )
        }

        androidx.compose.foundation.layout.FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Body1Normal(text = "${stringResource(Res.string.tv_amount)} :")
            Body2Medium(
                text = convertedAmount.toPlainString() + " ${exchangeRate.currency}",
                textAlign = TextAlign.Center
            )
        }
    }
    VerticalDivider(dp = 8.dp)

}

@OptIn(ExperimentalLayoutApi::class, ExperimentalResourceApi::class)
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    toSelectedCurrencyList: List<ExchangeRate>?,
    onRemove: (index: Int, item: ExchangeRate) -> Unit
) {
    AnimatedVisibility(toSelectedCurrencyList?.isNotEmpty() ?: false, enter = fadeIn()) {
        androidx.compose.foundation.layout.FlowRow(
            modifier = modifier.padding(1.dp)
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            toSelectedCurrencyList?.forEachIndexed { index, item ->
                val itemModifier = Modifier
                    .padding(8.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(8.dp)
                    )

                Row(
                    modifier = itemModifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val currencyName =
                        if (item.currencyName.isNotEmpty()) "(${item.currencyName})" else String.empty
                    ComposeIcon(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(20.dp)
                            .clickable {
                                onRemove.invoke(index, item)
                            },
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        icon = Res.drawable.ic_cross
                    )
                    Body1Normal(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(8.dp),
                        text = "${item.currency} " + currencyName,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyExposedDropdownMenuBox(
    placeHolderText: String,
    modifier: Modifier = Modifier,
    defaultText: String,
    currencyRateState: CurrencyConversionScreenState,
    onItemSelected: (selectedCurrency: ExchangeRate) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(defaultText) }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                label = {
                    Body2Medium(placeHolderText, textAlign = TextAlign.Center)
                },
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                currencyRateState.listOfCurrency?.forEach { item ->
                    val currencyName =
                        if (item.currencyName.isNotEmpty()) "(${item.currencyName})" else String.empty
                    DropdownMenuItem(
                        text = { Text(text = "${item.currency} " + currencyName) },
                        onClick = {
                            selectedText = "${item.currency} " + currencyName
                            onItemSelected.invoke(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}