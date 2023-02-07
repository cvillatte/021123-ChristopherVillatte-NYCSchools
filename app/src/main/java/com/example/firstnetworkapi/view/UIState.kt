package com.example.firstnetworkapi.view

import com.example.firstnetworkapi.model.SchoolsItem

sealed class UIState< out T> {
    object LOADING : UIState<Nothing>()
    data class SUCCESS(val response: T) : UIState<T>()
    data class ERROR(val error: Exception) : UIState<Nothing>()
}
