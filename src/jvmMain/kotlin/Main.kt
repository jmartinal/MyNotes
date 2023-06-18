// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.concurrent.thread

class AppState {
    val state = mutableStateOf(UiState())

    fun loadNotes(size: Int = 10) {
        thread {
            state.value = UiState(loading = true)
            getNotes(size) { state.value = UiState(notes = it) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val notes: List<Note>? = null
    )
}

@Composable
@Preview
fun App(appState: AppState) {

    if (appState.state.value.notes == null) {
        LaunchedEffect(true) { appState.loadNotes() }
    }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (appState.state.value.loading) {
                CircularProgressIndicator()
            } else {
                appState.state.value.notes?.let { NotesList(it) }
            }
        }
    }
}

@Composable
private fun NotesList(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notes) { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.8F)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.h6,
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
                    Text(item.description)
                }
            }
        }
    }
}

fun main() = application {
    val appState = AppState()
    Window(onCloseRequest = ::exitApplication) {
        App(appState)
    }
}
