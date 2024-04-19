package com.jmartinal.mynotes.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        ) { paddingValues ->
            UiContent(viewModel, Modifier.padding(paddingValues))
            if (viewModel.state.isSaved) {
                onClose()
            }

            if (viewModel.state.loading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun UiContent(viewModel: DetailViewModel, modifier: Modifier = Modifier) {

    val note = viewModel.state.note

    if (viewModel.state.loading) {
        CircularProgressIndicator()
    } else {
        Column(
            modifier = modifier.fillMaxSize()
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
}

@OptIn(ExperimentalMaterial3Api::class)
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
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
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
            Note.Type.entries.map {
                DropdownMenuItem(
                    text = { Text(it.name) },
                    onClick = { onValueChanged(it) }
                )
            }
        }
    }
}
