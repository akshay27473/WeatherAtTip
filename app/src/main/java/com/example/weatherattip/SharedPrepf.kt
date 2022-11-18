package com.example.weatherattip

import android.content.Context
import android.content.SharedPreferences

class SharedPreference (val context:Context) {
    private val preference_Name = "sharedprep"
    val shared_Pref: SharedPreferences =
        context.getSharedPreferences(preference_Name, Context.MODE_PRIVATE)

    /*Stored String Data*/
    fun save_String(key_name: String, text: String) {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.putString(key_name, text)
        editor.commit()
    }

    fun getPreferenceString(key_name: String): String? {
        return shared_Pref.getString(key_name, null)
    }

    fun clearSharedPreference(key_name: String) {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.remove(key_name) // removing the data on logout
        editor.commit()
    }
}