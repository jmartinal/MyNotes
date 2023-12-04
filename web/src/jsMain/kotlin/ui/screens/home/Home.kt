package ui.screens.home

import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.ui.screens.home.HomeViewModel

@Composable
fun Home(
    viewModel: HomeViewModel,
    onNoteClicked: (noteId: Long) -> Unit,
) {
    NotesList(
        notes = viewModel.state.filteredNotes.orEmpty(),
        onNoteClick = { onNoteClicked(it.id) }
    )
}