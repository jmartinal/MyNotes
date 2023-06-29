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
            listOf(
                Filter.All to "All",
                Filter.ByType(Note.Type.TEXT) to "Text",
                Filter.ByType(Note.Type.AUDIO) to "Audio"
            ).forEach { (filter, text) ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onFilterClicked(filter)
                    }
                ) {
                    Text(text)
                }
            }
        }
    }
}