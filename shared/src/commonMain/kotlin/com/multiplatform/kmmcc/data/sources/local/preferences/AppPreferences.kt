package com.multiplatform.kmmcc.data.sources.local.preferences

import com.multiplatform.kmmcc.common.Constants.ExchangeRateConstants.DEFAULT_CURRENCY
import com.multiplatform.kmmcc.common.KMMPreferences
import com.multiplatform.kmmcc.common.utils.empty
import com.multiplatform.kmmcc.data.sources.local.preferences.PreferenceKeys.BASE_CURRENCY
import com.multiplatform.kmmcc.data.sources.local.preferences.PreferenceKeys.TIME_STAMP

class AppPreferences (private val platformPreferences: KMMPreferences) {

    var baseCurrency: String
        get() = platformPreferences.getString(BASE_CURRENCY,DEFAULT_CURRENCY)
        set(value) {
            platformPreferences.put(BASE_CURRENCY, value)
        }

    var timeStamp: String
        get() = platformPreferences.getString(TIME_STAMP,String.empty)
        set(value) {
            platformPreferences.put(TIME_STAMP, value)

        }
}