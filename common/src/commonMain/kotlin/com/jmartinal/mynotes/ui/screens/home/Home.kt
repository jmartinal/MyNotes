package com.jmartinal.mynotes.ui.screens.home

import androidx.compose.runtime.Composable
import com.jmartinal.mynotes.ui.viewmodels.HomeViewModel

@Composable
expect fun Home(viewModel: HomeViewModel, onNoteClicked: (noteId: Long) -> Unit)