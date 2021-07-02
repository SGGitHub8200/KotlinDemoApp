package com.pratham.kotlindemo.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
//import com.google.gson.Gson


/**
 * This class is used to store the data in Shared Prefernces.
 * Shared Preferences allows to save and retrieve data in the form of key,value pair. */
class FastSave private constructor() {
    //Integer value is stored
    fun saveInt(key: String?, value: Int) {
        val editor = mSharedPreferences!!.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    //Integer value is retrived
    fun getInt(key: String, defaultValue: Int): Int {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getInt(key, defaultValue)
        } else defaultValue
    }

    //Boolean value is stored
    fun saveBoolean(key: String?, value: Boolean) {
        val editor = mSharedPreferences!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    //Boolean value is retrived
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getBoolean(key, defaultValue)
        } else defaultValue
    }

    //Float value is stored
    fun saveFloat(key: String?, value: Float) {
        val editor = mSharedPreferences!!.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    //Float value is retrived
    fun getFloat(key: String, defaultValue: Float): Float {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getFloat(key, defaultValue)
        } else defaultValue
    }

    //Long value is stored
    fun saveLong(key: String?, value: Long) {
        val editor = mSharedPreferences!!.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    //Long value is retrived
    fun getLong(key: String, defaultValue: Long): Long {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getLong(key, defaultValue)
        } else defaultValue
    }

    //String value is stored
    fun saveString(key: String?, value: String?) {
        val editor = mSharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }

    //String value is retrived
    fun getString(key: String, defaultValue: String?): String? {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getString(key, defaultValue)
        } else defaultValue
    }

/*    fun <T> saveObject(key: String?, `object`: T) {
        val objectString: String = Gson().toJson(`object`)
        val editor = mSharedPreferences!!.edit()
        editor.putString(key, objectString)
        editor.apply()
    }

    fun <T> getObject(key: String, classType: Class<T>?): T? {
        if (isKeyExists(key)) {
            val objectString = mSharedPreferences!!.getString(key, null)
            if (objectString != null) {
                return Gson().fromJson(objectString, classType)
            }
        }
        return null
    }*/

    fun clearSession() {
        val editor = mSharedPreferences!!.edit()
        editor.clear()
        editor.apply()
    }

    fun deleteValue(key: String): Boolean {
        if (isKeyExists(key)) {
            val editor = mSharedPreferences!!.edit()
            editor.remove(key)
            editor.apply()
            return true
        }
        return false
    }

    fun isKeyExists(key: String): Boolean {
        val map = mSharedPreferences!!.all
        return if (map.containsKey(key)) {
            true
        } else {
            Log.e("FastSave", "No element founded in sharedPrefs with the key $key")
            false
        }
    }

    companion object {
        var instance: FastSave? = null
            get() {
                if (field == null) {
                    validateInitialization()
                    synchronized(FastSave::class.java) {
                        if (field == null) {
                            field = FastSave()
                        }
                    }
                }
                return field
            }
            private set
        private var mSharedPreferences: SharedPreferences? = null
        fun init(context: Context?) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        private fun validateInitialization() {
            if (mSharedPreferences == null) throw FastException("FastSave Library must be initialized inside your application class by calling FastSave.init(getApplicationContext)")
        }
    }
}