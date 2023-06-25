package com.jmartinal.mynotes.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MyNotesAppBar() {
    TopAppBar(
        title = { Text("My notes") },
        actions = { FilterIconButton() }
    )
}