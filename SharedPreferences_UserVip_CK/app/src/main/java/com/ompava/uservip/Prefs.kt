package com.ompava.uservip

import android.content.Context

class Prefs(val context: Context) {

    val SHARED_NAME = "Mydtb"
    val SHARED_USER_NAME = "username"
    val SHARED_VIP = "vip"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveName(name:String){
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun saveVIP(vip:Boolean){
        storage.edit().putBoolean(SHARED_VIP, vip).apply()
    }

    fun getName():String{
        return storage.getString(SHARED_USER_NAME, "")!!

    }

    fun getVip():Boolean{
        return storage.getBoolean(SHARED_VIP, false)
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}