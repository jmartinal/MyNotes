package com.jmartinal.mynotes.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.data.Note.Companion.NEW_NOTE_ID

@Composable
fun Detail(
    viewModel: DetailViewModel,
    onClose: () -> Unit
) {

    MaterialTheme {
        Scaffold(
            topBar = { DetailTopBar(
                viewModel = viewModel,
                onClose = onClose,
                onSave = viewModel::save,
                onDelete = viewModel::delete
            ) }
        ) {
            if (viewModel.state.isSaved) {
                onClose()
            }

            if (viewModel.state.loading) {
                CircularProgressIndicator()
            } else {
                UiContent(viewModel)
            }
        }
    }
}

@Composable
private fun UiContent(viewModel: DetailViewModel) {

    val note = viewModel.state.note

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        OutlinedTextField(
            value = note.title,
            onValueChange = { viewModel.update(viewModel.state.note.copy(title = it)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Title") },
            maxLines = 1
        )
        TypeDropdown(
            value = note.type,
            onValueChanged = { viewModel.update(note.copy(type = it)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = note.description,
            onValueChange = { viewModel.update(note.copy(description = it)) },
            modifier = Modifier.fillMaxWidth().weight(1F),
            label = { Text("Description") }
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
    val note = viewModel.state.note
    TopAppBar(
        title = { Text(note.title) },
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close details")
            }
        },
        actions = {
            IconButton(onClick = onSave) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
            if (note.id != NEW_NOTE_ID)
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete note")
                }
        }
    )
}

@Composable
private fun TypeDropdown(value: Note.Type, onValueChanged: (Note.Type) -> Unit, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
        propagateMinConstraints = true
    ) {
        OutlinedTextField(
            value = value.toString(),
            onValueChange = { },
            readOnly = true,
            label = { Text("Type") },
            trailingIcon = {
                IconButton(
                    onClick = { isExpanded = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            Note.Type.values().map {
                DropdownMenuItem(onClick = { onValueChanged(it) }) {
                    Text(it.name)
                }
            }
        }
    }
}
