package com.jmartinal.mynotes.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.data.Filter

@Composable
fun MyNotesAppBar(
    onFilterClicked: (Filter) -> Unit
) {
    TopAppBar(
        title = { Text("My notes") },
        actions = { FilterIconButton(onFilterClicked) }
    )
}