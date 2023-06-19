import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AppState {
    var state by mutableStateOf(UiState())

    suspend fun loadNotes(coroutineScope: CoroutineScope, size: Int = 10) = coroutineScope.launch {
        state = state.copy(loading = true)
        state = state.copy(loading = false, notes = getNotes(size))
    }

    data class UiState(
        val loading: Boolean = false,
        val notes: List<Note>? = null
    )
}

