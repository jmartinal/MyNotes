package com.jmartinal.mynotes.ui.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jmartinal.mynotes.ui.components.MyNotesAppBar

@Composable
@Preview
fun Home(): Unit = with(HomeState) {

    val state by state.collectAsState()

    LaunchedEffect(true) {
        loadNotes(this)
    }

    MaterialTheme {
        Scaffold(
            topBar = { MyNotesAppBar(onFilterClicked = ::onFilterClicked) }
        ) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                if (state.loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    state.filteredNotes?.let { NotesList(it) }
                }
            }
        }
    }
}