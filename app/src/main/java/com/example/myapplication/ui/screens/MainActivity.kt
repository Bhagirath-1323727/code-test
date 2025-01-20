package com.example.myapplication.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.data.repo.CityRepo
import com.example.myapplication.viewmodel.CityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = CityRepo(this)
        val viewModel = CityViewModel(repository)
        setContent {
            CityScreen(viewModel = viewModel)
        }
    }
}
