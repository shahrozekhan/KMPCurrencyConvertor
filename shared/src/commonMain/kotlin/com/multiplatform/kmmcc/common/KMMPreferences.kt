package com.multiplatform.kmmcc.common

class KMMPreferences(private val context: KMMContext) {

    fun put(key: String, value: Int) {
        context.putInt(key, value)
    }

    fun put(key: String, value: String) {
        context.putString(key, value)
    }

    fun put(key: String, value: Boolean) {
        context.putBool(key, value)
    }

    fun getInt(key: String, default: Int): Int = context.getInt(key, default)


    fun getString(key: String, value: String): String = context.getString(key, value)


    fun getBool(key: String, default: Boolean): Boolean =
        context.getBool(key, default)

}