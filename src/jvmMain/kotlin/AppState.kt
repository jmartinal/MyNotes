import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.concurrent.thread

object AppState {
    var state by mutableStateOf(UiState())

    fun loadNotes(size: Int = 10) {
        thread {
            state = state.copy(loading = true)
            getNotes(size) { notes -> state = state.copy(loading = false, notes = notes) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val notes: List<Note>? = null
    )
}

