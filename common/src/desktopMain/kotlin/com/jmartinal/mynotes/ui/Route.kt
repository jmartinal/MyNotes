package com.jmartinal.mynotes.ui

sealed interface Route {
    object Home: Route
    data class Detail(val noteId: Long): Route
}