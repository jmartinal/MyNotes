package com.jmartinal.mynotes.ui.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.ui.components.MyNotesAppBar

@Composable
@Preview
fun Home(
    viewModel: HomeViewModel,
    onNoteClicked: (noteId: Long) -> Unit,
) {

    MaterialTheme {
        Scaffold(
            topBar = { MyNotesAppBar(onFilterClicked = viewModel::onFilterClicked) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNoteClicked(Note.NEW_NOTE_ID) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add note"
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                if (viewModel.state.loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    viewModel.state.filteredNotes?.let { NotesList(it) { note -> onNoteClicked(note.id) } }
                }
            }
        }
    }
}