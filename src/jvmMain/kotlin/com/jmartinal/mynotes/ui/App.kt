package com.jmartinal.mynotes.ui

import androidx.compose.runtime.*
import com.jmartinal.mynotes.data.Note
import com.jmartinal.mynotes.ui.screens.detail.Action
import com.jmartinal.mynotes.ui.screens.detail.Detail
import com.jmartinal.mynotes.ui.screens.home.Home

@Composable
fun App() {
    var route by remember { mutableStateOf<Route>(Route.Home) }

    route.let {
        when (it) {
            Route.Home -> Home(onCreateClicked = { route = Route.Detail(Note.NEW_NOTE_ID) })
            is Route.Detail -> Detail(
                noteId = it.noteId,
                onAction = { action ->
                    when (action) {
                        Action.Close -> route = Route.Home
                        Action.Save -> { }
                        Action.Delete -> { }
                    }
                }
            )
        }
    }
}
