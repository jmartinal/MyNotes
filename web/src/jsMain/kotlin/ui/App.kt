package ui

import androidx.compose.runtime.*
import com.jmartinal.mynotes.ui.Route
import com.jmartinal.mynotes.ui.screens.detail.DetailViewModel
import com.jmartinal.mynotes.ui.screens.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import ui.screens.detail.Detail
import ui.screens.home.Home

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
