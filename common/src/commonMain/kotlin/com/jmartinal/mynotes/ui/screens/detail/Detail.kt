package com.jmartinal.mynotes.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmartinal.mynotes.data.Note.Companion.NEW_NOTE_ID
import com.jmartinal.mynotes.ui.components.MyNotesAppBar
import com.jmartinal.mynotes.ui.components.TypeDropdown

@Composable
fun Detail(
    viewModel: DetailViewModel,
    onClose: () -> Unit
) {
    val note = viewModel.state.note

    MaterialTheme {
        Scaffold(
            topBar = {
                MyNotesAppBar(
                    title = note.title,
                    navigationIcon = {
                        IconButton(onClick = onClose) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close details")
                        }
                    },
                    actions = {
                        IconButton(onClick = viewModel::save) {
                            Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
                        }
                        if (note.id != NEW_NOTE_ID)
                            IconButton(onClick = viewModel::delete) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete note")
                            }
                    }
                )
            }
        ) { paddingValues ->
            if (viewModel.state.isSaved) {
                onClose()
            }

            when {
                viewModel.state.isLoading -> LoadingContent()
                else -> UiContent(
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
private fun UiContent(
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {
    val note = viewModel.state.note

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp)
    ) {
        OutlinedTextField(
            value = note.title,
            onValueChange = { viewModel.update(note.copy(title = it)) },
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
        Spacer(modifier = Modifier.height(32.dp))
    }

}


