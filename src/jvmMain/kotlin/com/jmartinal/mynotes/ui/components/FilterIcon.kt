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

    @Composable
    infix fun Filter.ToMenuItem(text: String) {
        DropdownMenuItem(
            onClick = {
                isExpanded = false
                onFilterClicked(this)
            }
        ) {
            Text(text)
        }
    }

    IconButton(onClick = { isExpanded = true }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter"
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            Filter.All ToMenuItem "All"
            Filter.ByType(Note.Type.TEXT) ToMenuItem "Text"
            Filter.ByType(Note.Type.AUDIO) ToMenuItem "Audio"
        }
    }
}