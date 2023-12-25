package com.multiplatform.kmmcc.common

expect class KMMContext()

expect fun KMMContext.putInt(key: String, value: Int)

expect fun KMMContext.getInt(key: String, default: Int): Int

expect fun KMMContext.putString(key: String, value: String)

expect fun KMMContext.getString(key: String,value: String) : String

expect fun KMMContext.putBool(key: String, value: Boolean)

expect fun KMMContext.getBool(key: String, default: Boolean): Boolean