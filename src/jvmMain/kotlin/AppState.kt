import androidx.compose.runtime.mutableStateOf
import kotlin.concurrent.thread

class AppState {
    val state = mutableStateOf(UiState())

    fun loadNotes(size: Int = 10) {
        thread {
            state.update { it.copy(loading = true) }
            getNotes(size) { notes -> state.update { it.copy(loading = false, notes = notes) } }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val notes: List<Note>? = null
    )
}

