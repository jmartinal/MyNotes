package com.jmartinal.mynotes.ui.screens.home

import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.data.remote.notesClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

object HomeState {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun loadNotes(coroutineScope: CoroutineScope) = coroutineScope.launch {
        _state.value = UiState(loading = true)
        val response = notesClient.request("http://localhost:8080/notes")
        _state.value = UiState(loading = false, notes = response.body())
        println(response.body() as String)
    }

    fun onFilterClicked(filter: Filter) {
        _state.update { it.copy(filter = filter) }
    }

    data class UiState(
        val loading: Boolean = false,
        val notes: List<Note>? = null,
        val filter: Filter = Filter.All
    ) {
        val filteredNotes get() = when(filter){
            Filter.All -> notes
            is Filter.ByType -> notes?.filter { it.type == filter.type }
        }
    }
}

