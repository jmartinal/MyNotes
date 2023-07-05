package com.jmartinal.mynotes.ui

import androidx.compose.runtime.*
import com.jmartinal.mynotes.ui.screens.detail.Detail
import com.jmartinal.mynotes.ui.screens.detail.DetailViewModel
import com.jmartinal.mynotes.ui.screens.home.Home
import com.jmartinal.mynotes.ui.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun App() {

    var route: Route by remember { mutableStateOf(Route.Home) }
    val scope: CoroutineScope = rememberCoroutineScope()

    route.let {
        when (it) {
            Route.Home -> Home(
                viewModel = HomeViewModel(scope),
                onNoteClicked = { noteId -> route = Route.Detail(noteId) }
            )

            is Route.Detail -> Detail(
                viewModel = DetailViewModel(scope, it.noteId),
                onClose = { route = Route.Home }
            )
        }
    }
}
