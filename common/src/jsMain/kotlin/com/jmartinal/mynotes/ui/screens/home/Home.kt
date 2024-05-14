package com.jmartinal.mynotes.ui.screens.home

import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.ui.components.MyNotesAppBar
import com.jmartinal.mynotes.ui.theme.AppStyleSheet
import com.jmartinal.mynotes.ui.viewmodels.HomeViewModel
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun Home(
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
            if (viewModel.state.isLoading) {
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