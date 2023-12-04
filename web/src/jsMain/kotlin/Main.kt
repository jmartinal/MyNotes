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

fun main() {

    renderComposable(rootElementId = "root") {
        val coroutineScope = rememberCoroutineScope()
        val homeViewModel = remember { HomeViewModel(coroutineScope) }

        NotesList(homeViewModel.state.filteredNotes.orEmpty()) { note ->
            console.log("Note clicked: $note")
        }
    }
}

@Composable
private fun NotesList(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                gap(8.px)
                width(100.percent)
                height(100.percent)
                alignItems(AlignItems.Center)
            }
        }
    ) {
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
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                width(80.percent)
                maxWidth(600.px)
                padding(16.px)
                border(1.px, LineStyle.Solid, Color.gray)
                borderRadius(4.px)
                cursor("pointer")
            }
        }
    ) {
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    alignItems(AlignItems.Center)
                }
            }
        ) {
            H3(
                attrs = {
                    style {
                        flex(1)
                    }
                }
            ) { Text(note.title) }
            if (note.type == Note.Type.AUDIO) Span { Text("🎙️") }
        }

        Div {
            Text(note.description)
        }
    }
}

