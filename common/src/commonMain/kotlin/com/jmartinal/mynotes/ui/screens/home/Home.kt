package com.jmartinal.mynotes.ui.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jmartinal.mynotes.ui.screens.detail.DetailScreen
import com.jmartinal.mynotes.ui.viewmodels.HomeViewModel


object HomeScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Home(
            viewModel = rememberScreenModel { HomeViewModel() },
            onNoteClicked = { noteId -> navigator.push(DetailScreen(noteId)) }
        )
    }
}
@Composable
expect fun Home(viewModel: HomeViewModel, onNoteClicked: (noteId: Long) -> Unit)