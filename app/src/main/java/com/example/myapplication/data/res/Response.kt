package com.example.myapplication.data.res

sealed interface Response<out T> {
    data class OnSuccess<T>(val data: T) : Response<T>
    data class OnError<T>(val message: String) : Response<T>
    object OnLoading : Response<Nothing>
}