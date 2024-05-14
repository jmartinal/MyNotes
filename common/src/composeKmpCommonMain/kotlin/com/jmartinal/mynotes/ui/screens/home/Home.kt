package com.jmartinal.mynotes.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.getAppTitle
import com.jmartinal.mynotes.ui.components.FilterIconButton
import com.jmartinal.mynotes.ui.components.MyNotesAppBar
import com.jmartinal.mynotes.ui.viewmodels.HomeViewModel

@Composable
actual fun Home(
    viewModel: HomeViewModel,
    onNoteClicked: (noteId: Long) -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                MyNotesAppBar(
                    title = getAppTitle(),
                    actions = { FilterIconButton(viewModel::onFilterClicked) }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNoteClicked(Note.NEW_NOTE_ID) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add note"
                    )
                }
            }
        ) { paddingValues ->
            when {
                viewModel.state.isLoading -> LoadingContent()
                else -> UiContent(
                    viewModel = viewModel,
                    onNoteClicked = { note -> onNoteClicked(note.id) },
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
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onNoteClicked: (note: Note) -> Unit = {}
) {
    viewModel.state.filteredNotes?.let { notes ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notes) { item ->
                Card(
                    modifier = Modifier.clickable { onNoteClicked(item) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.weight(1F)
                            )
                            if (item.type == Note.Type.AUDIO) {
                                Icon(
                                    imageVector = Icons.Default.Mic,
                                    contentDescription = "Audio note"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}