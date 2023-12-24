package com.multiplatform.kmmcc.di

import com.multiplatform.kmmcc.data.gateway.ExchangeRateGateway
import com.multiplatform.kmmcc.data.repository.ExchangeRateRepositoryImpl
import com.multiplatform.kmmcc.data.repository.FavoriteExchangeRateRepositoryImpl
import com.multiplatform.kmmcc.data.sources.RemoteErrorParser
import com.multiplatform.kmmcc.data.sources.KtorServiceHelper
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

fun init() = startKoin {
    modules(ApplicationKoinComponentModules)
}


internal val ApplicationKoinComponentModules =
    platformKoinModule() +
            appModules() +
            provideDispatchers() +
            provideRepositories() +
            provideUseCases() +
            provideViewModel() +
            provideGatway() +
            httpClientModule


fun provideDispatchers() = module {
    single { Dispatchers.IO }
    single { Dispatchers.Main }
    single { Dispatchers.Default }
}

fun provideRepositories() = module {
    factory<ExchangeRateRepository> { ExchangeRateRepositoryImpl(get(), get()) }
    factory<FavoriteExchangeRateRepository> { FavoriteExchangeRateRepositoryImpl(get()) }
}

fun provideUseCases() = module {

    factory { ConvertExchangeRateUseCase(get()) }
    factory { ForceSyncExchangeRatesUseCase(get(), get()) }
    factory { GetExchangeRateUseCase(get(), get()) }
    factory { GetFavoriteExchangeRateUseCase(get()) }
    factory { MarkExchangeRateToFavoriteUseCase(get(), get()) }

}

fun provideViewModel() = module {
    viewModelDefinition { ExchangeRateViewModel(get(), get(), get(), get(), get()) }
}

fun provideGatway() = module {
    single { ExchangeRateGateway(get()) }
}

fun appModules() = module {
    single { RemoteErrorParser() }
    single { KtorServiceHelper(get()) }
}
