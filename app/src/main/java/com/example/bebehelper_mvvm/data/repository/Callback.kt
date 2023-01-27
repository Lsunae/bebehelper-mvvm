package com.example.bebehelper_mvvm.data.repository

interface Callback<T> {
    fun onSuccess(response: T)
    fun onFailure(message: String)
}