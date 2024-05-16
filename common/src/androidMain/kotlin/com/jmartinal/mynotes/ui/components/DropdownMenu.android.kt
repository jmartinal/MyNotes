package com.jmartinal.mynotes.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable

@Composable
actual fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        content = content
    )
}

@Composable
actual fun DropdownMenuItem(onClick: () -> Unit, text: @Composable () -> Unit) {
    DropdownMenuItem(
        onClick = onClick,
        text = text
    )
}