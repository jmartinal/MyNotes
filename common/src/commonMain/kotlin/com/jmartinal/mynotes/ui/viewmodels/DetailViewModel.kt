package com.jmartinal.mynotes.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.data.remote.NotesRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val noteId: Long) : ScreenModel {

    var state by mutableStateOf(UiState())
        private set

    init {
        if (noteId != Note.NEW_NOTE_ID) {
            loadNote()
        }
    }

    private fun loadNote() {
        screenModelScope.launch {
            state = UiState(isLoading = true)
            state = UiState(note = NotesRepository.getById(noteId))
        }
    }

    fun save() {
        screenModelScope.launch {
            val note = state.note
            if (note.id == Note.NEW_NOTE_ID) {
                NotesRepository.save(note)
            } else {
                NotesRepository.update(note)
            }
            state = state.copy(isSaved = true)
        }
    }

    fun update(note: Note) {
        state = state.copy(note = note)
    }

    fun delete() {
        screenModelScope.launch {
            NotesRepository.delete(state.note)
            state = state.copy(isSaved = true)
        }
    }

    data class UiState(
        val note: Note = Note("", "", Note.Type.TEXT),
        val isLoading: Boolean = false,
        val isSaved: Boolean = false,
    )
}
