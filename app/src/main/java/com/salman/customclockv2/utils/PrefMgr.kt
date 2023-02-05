package com.salman.customclockv2.utils

import android.content.Context
import android.content.SharedPreferences


class PrefMgr {

    var sharedPref : SharedPreferences

    constructor(ctxt : Context ){
        sharedPref = ctxt.getSharedPreferences(ctxt.packageName, Context.MODE_PRIVATE)
    }

    fun putObj(key: String, myObj: Any) {
        val editor = sharedPref.edit()
        when (myObj) {
            is String -> {
                editor.putString(key, myObj)
            }
            is Int -> {
                editor.putInt(key, myObj)
            }
            else -> {
                editor.putBoolean(key, myObj as Boolean)
            }
        }
        editor.apply()
    }

    fun getObj( key: String, defVal: Any): Any {
        return when (defVal) {
            is String -> {
                sharedPref.getString(key, defVal) as Any
            }
            is Int -> {
                sharedPref.getInt(key, defVal)
            }
            else -> {
                sharedPref.getBoolean(key, defVal as Boolean)
            }
        }
    }

}