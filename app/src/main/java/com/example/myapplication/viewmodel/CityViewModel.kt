package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.City
import com.example.myapplication.data.repo.CityRepo
import com.example.myapplication.data.res.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityViewModel(private val repo: CityRepo) : ViewModel() {
    private val citiesList = MutableStateFlow<Response<Map<String, List<City>>>>(Response.OnLoading)
    val cities: StateFlow<Response<Map<String, List<City>>>> get() = citiesList

    init {
        fetchCities()
    }

    private fun fetchCities() {
        viewModelScope.launch {
            try {
                citiesList.value = Response.OnLoading
                val cities = loadCitiesFromJson()
                val groupByCities = cities.groupBy { it.admin_name }
                citiesList.value = Response.OnSuccess(groupByCities)
            } catch (e: Exception) {
                citiesList.value = Response.OnError("Failed to load cities")
            }
        }
    }

    private fun loadCitiesFromJson(): List<City> {
        return repo.getCityList()
    }

    fun sort() {
        val currentState = citiesList.value
        if (currentState is Response.OnSuccess) {
            val reversedGroupedCities =
                currentState.data.mapValues { (_, cities) -> cities.reversed() }
            citiesList.value = Response.OnSuccess(reversedGroupedCities)
        }

    }
}