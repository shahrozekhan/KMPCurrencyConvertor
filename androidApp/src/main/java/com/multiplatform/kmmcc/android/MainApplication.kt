package com.multiplatform.kmmcc.android

import android.app.Application
import com.multiplatform.kmmcc.di.injectKoin

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        injectKoin()
    }
}