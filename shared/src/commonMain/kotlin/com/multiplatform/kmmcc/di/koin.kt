package com.multiplatform.kmmcc.di

import com.multiplatform.kmmcc.common.KMMContext
import com.multiplatform.kmmcc.common.KMMPreferences
import com.multiplatform.kmmcc.data.sources.remote.gateway.ExchangeRateGateway
import com.multiplatform.kmmcc.data.repository.ExchangeRateRepositoryImpl
import com.multiplatform.kmmcc.data.repository.FavoriteExchangeRateRepositoryImpl
import com.multiplatform.kmmcc.data.sources.RemoteErrorParser
import com.multiplatform.kmmcc.data.sources.local.AppPreferences
import com.multiplatform.kmmcc.data.sources.remote.KtorServiceHelper
import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import com.multiplatform.kmmcc.domain.usecases.conversion.ConvertExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.ForceSyncExchangeRatesUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.GetExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.favorite.GetFavoriteExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.favorite.MarkExchangeRateToFavoriteUseCase
import com.multiplatform.kmmcc.platformKoinModule
import com.multiplatform.kmmcc.presentation.components.ExchangeRateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun injectKoin() = init()

internal fun init() = startKoin {
    modules(ApplicationKoinComponentModules)
}


internal val ApplicationKoinComponentModules =
    platformKoinModule() +
            commonMainModules() +
            provideDispatchers() +
            provideRepositories() +
            provideUseCases() +
            provideViewModel() +
            provideGatway() +
            httpClientModule


internal fun provideDispatchers() = module {
    single { Dispatchers.IO }
    single { Dispatchers.Main }
    single { Dispatchers.Default }
}

internal fun provideRepositories() = module {
    factory<ExchangeRateRepository> { ExchangeRateRepositoryImpl(get(), get(), get()) }
    factory<FavoriteExchangeRateRepository> { FavoriteExchangeRateRepositoryImpl(get()) }
}

internal fun provideUseCases() = module {

    factory { ConvertExchangeRateUseCase(get()) }
    factory { ForceSyncExchangeRatesUseCase(get(), get()) }
    factory { GetExchangeRateUseCase(get(), get(),get()) }
    factory { GetFavoriteExchangeRateUseCase(get()) }
    factory { MarkExchangeRateToFavoriteUseCase(get(), get()) }

}

fun provideViewModel() = module {
    viewModelDefinition { ExchangeRateViewModel(get(), get(), get(), get(), get()) }
}

fun provideGatway() = module {
    single { ExchangeRateGateway(get()) }
}

fun commonMainModules() = module {
    single { RemoteErrorParser() }
    single { KtorServiceHelper(get()) }
    single { KMMContext() }
    single { KMMPreferences(get()) }
    single { AppPreferences(get()) }
}
