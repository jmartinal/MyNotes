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
fun Detail(noteId: Long, onAction: (Action) -> Unit) {

    var note by remember { mutableStateOf(Note("Note", "Mocked description", Note.Type.TEXT, 2)) }

    MaterialTheme {
        Scaffold(
            topBar = { DetailTopBar(note = note, onAction = onAction) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                OutlinedTextField(
                    value = note.title,
                    onValueChange = { note = note.copy(title = it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Title") },
                    maxLines = 1
                )
                TypeDropdown(
                    value = note.type,
                    onValueChanged = { note = note.copy(type = it) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = note.description,
                    onValueChange = { note = note.copy(description = it) },
                    modifier = Modifier.fillMaxWidth().weight(1F),
                    label = { Text("Description") }
                )
            }
        }
    }
}

@Composable
private fun DetailTopBar(
    note: Note,
    onAction: (Action) -> Unit,
) {
    TopAppBar(
        title = { Text(note.title) },
        navigationIcon = {
            IconButton(onClick = { onAction(Action.Close) }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close details")
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onAction(Action.Save)

                    onAction(Action.Close)
                }
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
            if (note.id != NEW_NOTE_ID)
                IconButton(
                    onClick = {
                        onAction(Action.Delete)

                        onAction(Action.Close)
                    }
                ) {
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
