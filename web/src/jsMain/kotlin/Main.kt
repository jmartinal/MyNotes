import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.ui.screens.home.HomeViewModel
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import ui.theme.AppStyleSheet

fun main() {

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)

        val coroutineScope = rememberCoroutineScope()
        val homeViewModel = remember { HomeViewModel(coroutineScope) }

        NotesList(homeViewModel.state.filteredNotes.orEmpty()) { note ->
            console.log("Note clicked: $note")
        }
    }
}

@Composable
private fun NotesList(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    Div(attrs = { classes(AppStyleSheet.notesList) }) {
        notes.forEach { note ->
            NoteCard(note, onNoteClick)
        }
    }
}

@Composable
private fun NoteCard(note: Note, onNoteClick: (Note) -> Unit) {
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
            if (note.type == Note.Type.AUDIO) Span { Text("🎙️") }
        }

        Div {
            Text(note.description)
        }
    }
}

