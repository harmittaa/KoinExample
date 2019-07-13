package com.github.harmittaa.koinexample.persistence

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { ExamplePreferences(androidContext()) }
}

class ExamplePreferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val showFragmentKey = "showFragment"
    private val fragmentContentKey = "fragmentContent"

    init {
        storeShouldShowFragment(true)
        storeFragmentContent("Hey, this is a fragment")
    }

    private fun storeFragmentContent(content: String) {
        preferences.edit().putString(fragmentContentKey, content).apply()
    }

    fun getFragmentContent(): String? {
        return preferences.getString(fragmentContentKey, "")
    }

    private fun storeShouldShowFragment(shouldShow: Boolean) {
        preferences.edit().putBoolean(showFragmentKey, shouldShow).apply()
    }

    fun getShouldShowFragment(): Boolean {
        return preferences.getBoolean(showFragmentKey, false)
    }


}