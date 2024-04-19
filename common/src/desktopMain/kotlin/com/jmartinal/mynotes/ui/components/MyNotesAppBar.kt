package com.jmartinal.mynotes.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.data.Filter
import com.jmartinal.mynotes.getAppTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNotesAppBar(
    onFilterClicked: (Filter) -> Unit
) {
    TopAppBar(
        title = { Text(text = getAppTitle()) },
        actions = { FilterIconButton(onFilterClicked) },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}