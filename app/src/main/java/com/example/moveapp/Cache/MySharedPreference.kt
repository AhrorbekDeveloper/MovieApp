package com.example.moveapp.Cache

import android.content.Context
import android.content.SharedPreferences
import com.example.moveapp.Models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Cache {
    private const val NAME = "KeshXotiraga"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        preferences = context.getSharedPreferences(
            NAME,
            MODE
        )
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var obektString: ArrayList<User>
        get() = gsonStringToArray(preferences.getString("obekt", "[]")!!)
        set(value) = preferences.edit {
            if (value != null){
                it.putString("obekt", arrayToGsonString(value))
            }
        }

    fun arrayToGsonString(arrayList: ArrayList<User>): String{
        return Gson().toJson(arrayList)
    }

    fun gsonStringToArray(gsonString: String): ArrayList<User>{
        val list = ArrayList<User>()
        val typeToken = object : TypeToken<ArrayList<User>>(){}.type
        list.addAll(Gson().fromJson(gsonString, typeToken))
        return list
    }
}