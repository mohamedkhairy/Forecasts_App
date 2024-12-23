package com.example.utils.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


inline fun <reified T> T.toJsonString(): String {
    val gson = Gson()
    return gson.toJson(this)
}

inline fun <reified T> String.jsonParse(): T {
    val typeToken = object : TypeToken<T>() {}.type
    val gson = Gson()
    return gson.fromJson(this, typeToken)
}

fun Any?.isNotNull(): Boolean =
    this != null
