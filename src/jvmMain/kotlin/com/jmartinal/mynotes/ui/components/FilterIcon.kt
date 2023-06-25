package com.jmartinal.mynotes.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*

@Composable
fun FilterIconButton() {
    var isExpanded by remember { mutableStateOf(false) }

    IconButton(onClick = { isExpanded = true }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter"
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(onClick = { isExpanded = false }) {
                Text("All")
            }
            DropdownMenuItem(onClick = { isExpanded = false }) {
                Text("Text")
            }
            DropdownMenuItem(onClick = { isExpanded = false }) {
                Text("Audio")
            }
        }
    }
}
