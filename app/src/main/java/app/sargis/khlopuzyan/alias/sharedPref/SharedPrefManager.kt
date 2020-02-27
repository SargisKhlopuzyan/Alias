package app.sargis.khlopuzyan.alias.sharedPref

import android.content.Context
import javax.inject.Inject

/**
 * Created by Sargis Khlopuzyan, on 2/27/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
class SharedPrefManager @Inject constructor(var context: Context) {

    companion object {
        const val SHARED_PREF_SETTINGS = "SHARED_PREF_SETTINGS"
    }

    fun storeStringInSharedPref(key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_SETTINGS, 0)
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun storeIntInSharedPref(key: String, value: Int) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_SETTINGS, 0)
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun storeBooleanInSharedPref(key: String, value: Boolean) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_SETTINGS, 0)
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun loadStringFromSharedPref(key: String): String {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_SETTINGS, 0)
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun loadIntFromSharedPref(key: String): Int {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_SETTINGS, 0)
        return sharedPreferences.getInt(key, 0)
    }

    fun loadBooleanFromSharedPref(key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREF_SETTINGS, 0)
        return sharedPreferences.getBoolean(key, false)
    }
}