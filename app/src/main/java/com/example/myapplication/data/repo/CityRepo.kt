package com.example.myapplication.data.repo

import android.content.Context
import com.example.myapplication.data.model.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CityRepo(private val context: Context) {
    fun getCityList(): List<City> {
        val gson = Gson()
        val jsonString =
            context.assets.open("au_cities.json").bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<City>>() {}.type
        return gson.fromJson(jsonString, listType)
    }
}