package com.jmartinal.mynotes.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.data.remote.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(private val scope: CoroutineScope) {

    var state by mutableStateOf(UiState())
        private set

    init {
        loadNotes()
    }

    private fun loadNotes() = scope.launch {
        state = UiState(isLoading = true)
        state = UiState(isLoading = false, notes = NotesRepository.getAll())
    }

    fun onFilterClicked(filter: Filter) {
        state = state.copy(filter = filter)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val notes: List<Note>? = null,
        val filter: Filter = Filter.All
    ) {
        val filteredNotes get() = when(filter){
            Filter.All -> notes
            is Filter.ByType -> notes?.filter { it.type == filter.type }
        }
    }
}

