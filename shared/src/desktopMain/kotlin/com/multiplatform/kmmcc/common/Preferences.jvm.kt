package com.multiplatform.kmmcc.common

import java.util.Properties
import java.util.prefs.Preferences

actual typealias KMMContext = Properties

actual fun KMMContext.putInt(key: String, value: Int) {
    prefs.putInt(key, value)
}

actual fun KMMContext.getInt(key: String, default: Int): Int {
    return prefs.getInt(key,default)
}

actual fun KMMContext.putString(key: String, value: String) {
    prefs.put(key,value) 
}

actual fun KMMContext.getString(key: String, value: String): String {
    return prefs.get(key,value) 
}

actual fun KMMContext.putBool(key: String, value: Boolean) {
    prefs.putBoolean(key,value)
}

actual fun KMMContext.getBool(key: String, default: Boolean): Boolean {
    return prefs.getBoolean(key,default)
}

private val prefs = Preferences.userRoot().node("kmm_app")
