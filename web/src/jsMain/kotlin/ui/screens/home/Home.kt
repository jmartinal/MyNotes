package ui.screens.home

import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.ui.screens.home.HomeViewModel
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import ui.components.MyNotesAppBar
import ui.theme.AppStyleSheet

@Composable
fun Home(
    viewModel: HomeViewModel,
    onNoteClicked: (noteId: Long) -> Unit,
) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                width(100.percent)
                height(100.percent)
            }
        }
    ) {
        MyNotesAppBar(onFilterClicked = viewModel::onFilterClicked)

        Div {
            if (viewModel.state.loading) {
                Text("Loading")
            }

            viewModel.state.filteredNotes?.let { notes ->
                NotesList(
                    notes = notes,
                    onNoteClick = { onNoteClicked(it.id) }
                )
            }
        }

        Div(attrs = {
            classes(AppStyleSheet.fab)
            onClick { onNoteClicked(Note.NEW_NOTE_ID) }
        }) {
            Text("+")
        }
    }

}