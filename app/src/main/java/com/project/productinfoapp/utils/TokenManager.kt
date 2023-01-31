package com.project.productinfoapp.utils

import android.content.Context
import android.content.SharedPreferences

import com.project.productinfoapp.utils.Constants.PREFS_TOKEN_FILE
import com.project.productinfoapp.utils.Constants.USER_IMAGE
import com.project.productinfoapp.utils.Constants.USER_NAME
import com.project.productinfoapp.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(token: String, name: String, image: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.putString(USER_NAME, name)
        editor.putString(USER_IMAGE, image)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
    fun getusername(): String? {
        return prefs.getString(USER_NAME, null)
    }
    fun getuserimage(): String? {
        return prefs.getString(USER_IMAGE, null)
    }

    fun delete() {
        println("token...deleted")
        val editor = prefs.edit()
        editor.clear()
        editor.commit()
    }
}