package com.jmartinal.mynotes.ui.screens.home

import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.data.getNotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

object HomeState {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun loadNotes(coroutineScope: CoroutineScope, size: Int = 10) = coroutineScope.launch {
        _state.value = UiState(loading = true)
        getNotes(size).collect {
            _state.value = UiState(loading = false, notes = it)
        }
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

