package com.jmartinal.mynotes.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmartinal.mynotes.data.Note

@Composable
fun NotesList(notes: List<Note>) {
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