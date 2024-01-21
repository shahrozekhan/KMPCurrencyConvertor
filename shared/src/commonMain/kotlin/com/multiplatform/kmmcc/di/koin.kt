package com.multiplatform.kmmcc.di

import app.cash.sqldelight.db.SqlDriver
import com.multiplatform.kmmcc.common.KMMContext
import com.multiplatform.kmmcc.common.KMMPreferences
import com.multiplatform.kmmcc.data.repository.ExchangeRateRepositoryImpl
import com.multiplatform.kmmcc.data.repository.FavoriteExchangeRateRepositoryImpl
import com.multiplatform.kmmcc.data.sources.RemoteErrorParser
import com.multiplatform.kmmcc.data.sources.local.preferences.AppPreferences
import com.multiplatform.kmmcc.data.sources.local.filereader.LocalJsonFileDataSource
import com.multiplatform.kmmcc.data.sources.remote.gateway.ExchangeRateDataSource
import com.multiplatform.kmmcc.database.ExchangeRateDB
import com.multiplatform.kmmcc.domain.repository.ExchangeRateRepository
import com.multiplatform.kmmcc.domain.repository.FavoriteExchangeRateRepository
import com.multiplatform.kmmcc.domain.usecases.conversion.ConvertExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.ForceSyncExchangeRatesUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.GetExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.exchangerate.SaveFromExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.favorite.GetFavoriteExchangeRateUseCase
import com.multiplatform.kmmcc.domain.usecases.favorite.MarkExchangeRateToFavoriteUseCase
import com.multiplatform.kmmcc.platformKoinModule
import com.multiplatform.kmmcc.presentation.components.ExchangeRateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun injectKoin(application: KMMContext, sqlDriver: SqlDriver) =
    init(application, sqlDriver)

internal fun init(application: KMMContext, sqlDriver: SqlDriver) = startKoin {
    modules(applicationKoinComponentModules(application = application, sqlDriver = sqlDriver))
}


internal fun applicationKoinComponentModules(
    application: KMMContext,
    sqlDriver: SqlDriver
) =
    platformKoinModule() +
            commonMainModules(application) +
            provideDispatchers() +
            provideRepositories() +
            provideUseCases() +
            provideViewModel() +
            provideGatway() +
            httpClientModule +
            provideDatabase(sqlDriver)

internal fun provideDispatchers() = module {
    single { Dispatchers.IO }
    single { Dispatchers.Main }
    single { Dispatchers.Default }
}

internal fun provideRepositories() = module {
    factory<ExchangeRateRepository> {
        ExchangeRateRepositoryImpl(
            get(),
            get(),
            Dispatchers.Default,
            get(),
            get()
        )
    }
    factory<FavoriteExchangeRateRepository> { FavoriteExchangeRateRepositoryImpl(get(), get()) }
}

internal fun provideUseCases() = module {

    factory { ConvertExchangeRateUseCase(get()) }
    factory { ForceSyncExchangeRatesUseCase(get(), get()) }
    factory { GetExchangeRateUseCase(get(), get(), get()) }
    factory { GetFavoriteExchangeRateUseCase(get()) }
    factory { MarkExchangeRateToFavoriteUseCase(get(), get()) }
    factory { SaveFromExchangeRateUseCase(get()) }

}

fun provideViewModel() = module {
    viewModelDefinition { ExchangeRateViewModel(get(), get(), get(), get(), get(), get()) }
}

fun provideGatway() = module {
    single { ExchangeRateDataSource(get()) }
}

fun provideDatabase(sqlDriver: SqlDriver) = module {
    single { ExchangeRateDB(sqlDriver) }
}

fun commonMainModules(context: KMMContext) = module {
    single { RemoteErrorParser() }
    single { KMMPreferences(context) }
    single { AppPreferences(get()) }
    single { LocalJsonFileDataSource() }
}
