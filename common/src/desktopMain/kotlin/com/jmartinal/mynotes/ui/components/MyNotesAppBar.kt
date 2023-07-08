package com.jmartinal.mynotes.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.getAppTitle

@Composable
fun MyNotesAppBar(
    onFilterClicked: (Filter) -> Unit
) {
    TopAppBar(
        title = { Text(text = getAppTitle()) },
        actions = { FilterIconButton(onFilterClicked) }
    )
}