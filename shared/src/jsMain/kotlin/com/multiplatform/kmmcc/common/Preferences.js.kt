package com.multiplatform.kmmcc.common

import kotlinx.browser.localStorage
import org.w3c.dom.Storage
import org.w3c.dom.set


actual typealias KMMContext = Any

actual fun KMMContext.putInt(key: String, value: Int) {
    localStorage.setItem(key, value.toString())
}

actual fun KMMContext.getInt(key: String, default: Int): Int {
    return localStorage.getItem(key)?.toInt()?:0
}

actual fun KMMContext.putString(key: String, value: String) {
    localStorage.setItem(key, value)
}

actual fun KMMContext.getString(key: String, value: String): String {
    return localStorage.getItem(key)?:value
}

actual fun KMMContext.putBool(key: String, value: Boolean) {
    localStorage.set(key, value.toString())
}

actual fun KMMContext.getBool(key: String, default: Boolean): Boolean {
    return localStorage.getItem(key).toBoolean()
}

class storage : Storage()


