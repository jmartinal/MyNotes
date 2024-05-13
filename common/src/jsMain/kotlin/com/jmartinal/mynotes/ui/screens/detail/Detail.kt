package com.jmartinal.mynotes.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.data.Note.Companion.NEW_NOTE_ID
import com.jmartinal.mynotes.ui.viewmodels.DetailViewModel
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.selected
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import com.jmartinal.mynotes.ui.components.Icon
import com.jmartinal.mynotes.ui.theme.AppStyleSheet

@Composable
actual fun Detail(viewModel: DetailViewModel, onClose: () -> Unit) {
    Div {
        DetailTopBar(
            viewModel = viewModel,
            onClose = onClose,
            onSave = viewModel::save,
            onDelete = viewModel::delete
        )

        if (viewModel.state.isSaved) onClose()

        if (viewModel.state.isLoading) Text("Loading")
        else UiContent(viewModel)
    }
}

@Composable
private fun UiContent(viewModel: DetailViewModel) {

    val note = viewModel.state.note

    Div(
        attrs = {
            style {
                padding(32.px)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                gap(16.px)
                maxWidth(600.px)
                property("margin", "0 auto")
            }
        }
    ) {
        TextInput(
            value = note.title,
            attrs = {
                placeholder("Title")
                classes(AppStyleSheet.detailInput)
                onInput { viewModel.update(note.copy(title = it.value)) }
            }
        )

        TypeDropdown(
            value = note.type,
            onValueChanged = { viewModel.update(note.copy(type = it)) }
        )

        TextArea(
            value = note.description,
            attrs = {
                placeholder("Description")
                classes(AppStyleSheet.detailInput)
                onInput { viewModel.update(note.copy(description = it.value)) }
            }
        )
    }
}

@Composable
private fun DetailTopBar(
    viewModel: DetailViewModel,
    onClose: () -> Unit,
    onSave: () -> Unit,
    onDelete: () -> Unit,
) {
    Div(attrs = { classes(AppStyleSheet.topBar) }) {
        Icon(
            name = "close",
            attrs = {
                classes(AppStyleSheet.topBarIcon)
                onClick { onClose() }
            }
        )

        H1(attrs = { classes(AppStyleSheet.topBarTitle) }) {
            Text(viewModel.state.note.title)
        }

        Icon(
            name = "save",
            attrs = {
                classes(AppStyleSheet.topBarIcon)
                onClick { onSave() }
            }
        )

        if (viewModel.state.note.id != NEW_NOTE_ID)
            Icon(
                name = "delete",
                attrs = {
                    classes(AppStyleSheet.topBarIcon)
                    onClick { onDelete() }
                }
            )
    }
}

@Composable
private fun TypeDropdown(value: Note.Type, onValueChanged: (Note.Type) -> Unit, modifier: Modifier = Modifier) {
    Select(
        attrs = {
            classes(AppStyleSheet.detailInput)
            onChange { onValueChanged(Note.Type.valueOf(it.target.value)) }
        }
    ) {
        Note.Type.entries.forEach {
            Option(
                value = it.name,
                attrs = { if (it == value) selected() }
            ) {
                Text(it.name)
            }
        }
    }
}
