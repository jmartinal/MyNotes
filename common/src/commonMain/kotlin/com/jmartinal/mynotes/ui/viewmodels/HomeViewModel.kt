package com.jmartinal.mynotes.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.data.remote.NotesRepository
import kotlinx.coroutines.launch

class HomeViewModel: ScreenModel {

    var state by mutableStateOf(UiState())
        private set

    init {
        loadNotes()
    }

    private fun loadNotes() = screenModelScope.launch {
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

