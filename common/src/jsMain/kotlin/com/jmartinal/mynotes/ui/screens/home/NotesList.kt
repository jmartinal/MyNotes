package com.jmartinal.mynotes.ui.screens.home

import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.data.Note
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import com.jmartinal.mynotes.ui.components.Icon
import com.jmartinal.mynotes.ui.theme.AppStyleSheet

@Composable
fun NotesList(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    Div(attrs = { classes(AppStyleSheet.notesList) }) {
        notes.forEach { note ->
            NoteCard(note, onNoteClick)
        }
    }
}

@Composable
fun NoteCard(note: Note, onNoteClick: (Note) -> Unit) {
    Div(
        attrs = {
            onClick { onNoteClick(note) }
            classes(AppStyleSheet.noteCard)
        }
    ) {
        Div(attrs = { classes(AppStyleSheet.noteCardHeader) }
        ) {
            H3(attrs = { classes(AppStyleSheet.noteTitle) }) {
                Text(note.title)
            }
            if (note.type == Note.Type.AUDIO)
                Icon(
                    name = "mic"
                )
        }

        Div {
            Text(note.description)
        }
    }
}
