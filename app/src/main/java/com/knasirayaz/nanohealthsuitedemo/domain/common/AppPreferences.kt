package com.knasirayaz.nanohealthsuitedemo.domain.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.knasirayaz.nanohealthsuitedemo.R

class AppPreferences(context: Context, val gson: Gson) {

    private var prefs: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        editor = prefs.edit()
    }

    /**All puts methods of share preferences */

    fun contains(key: String) = prefs.contains(key)

    fun putString(key: String, value: String) {
        editor.putString(key, value).commit()
    }

    fun putBoolean(key: String, value: Boolean?) {
        editor.putBoolean(key, value!!).commit()
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value).commit()
    }

    fun putFloat(key: String, value: Float?) {
        editor.putFloat(key, value!!).commit()
    }

    fun putLong(key: String, value: Long) {
        editor.putLong(key, value).commit()
    }

    fun putList(key: String, list: List<*>) {
        val json = gson.toJson(list)
        putString(key,json)
    }


    /**end  */

    /**All Gets methods of share preferences */

    fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    fun getInt(badgeCounter: String, key: String): Int {
        return prefs.getInt(key, 0)
    }

    fun getBoolean(key: String): Boolean? {
        return prefs.getBoolean(key, false)
    }

    fun getFloat(key: String): Float {
        return prefs.getFloat(key, 0.0f)
    }

    fun getString(key: String, defValue: String): String? {
        return prefs.getString(key, defValue)
    }

    internal inline fun <reified T> getList(key: String): T {
        val json = getString(key,"")
        return gson.fromJson(json, object: TypeToken<T>(){}.type)
    }

    /**end  */

    fun removeString(key: String) {
        editor.remove(key).commit()
    }

    /**putting whole object from web server and save in share preferences */
    fun putString(key: String, obj: Any) {
        editor.putString(key, gson.toJson(obj))
    }
    /**end  */

    /**getting whole object from web server and save in share preferences */

    fun <A> getObject(key: String, type: Class<A>): A {
        return gson.fromJson(getString(key), type)
    }


    /**end  */
}
