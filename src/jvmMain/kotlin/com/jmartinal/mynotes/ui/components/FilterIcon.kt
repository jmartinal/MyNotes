package com.jmartinal.mynotes.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.data.Note

@Composable
fun FilterIconButton(
    onFilterClicked: (Filter) -> Unit
) {
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
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onFilterClicked(Filter.All)
                }
            ) {
                Text("All")
            }
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onFilterClicked(Filter.ByType(Note.Type.TEXT))
                })
            {
                Text("Text")
            }
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onFilterClicked(Filter.ByType(Note.Type.AUDIO))
                })
            {
                Text("Audio")
            }
        }
    }
}