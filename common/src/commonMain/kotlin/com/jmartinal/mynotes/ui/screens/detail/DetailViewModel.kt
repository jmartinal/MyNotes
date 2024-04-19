package com.jmartinal.mynotes.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.data.remote.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailViewModel(private val scope: CoroutineScope, private val noteId: Long) {

    var state by mutableStateOf(UiState())
        private set

    init {
        if (noteId != Note.NEW_NOTE_ID) {
            loadNote()
        }
    }

    private fun loadNote() {
        scope.launch {
            state = UiState(isLoading = true)
            state = UiState(note = NotesRepository.getById(noteId))
        }
    }

    fun save() {
        scope.launch {
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
        scope.launch {
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
