import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object AppState {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    suspend fun loadNotes(coroutineScope: CoroutineScope, size: Int = 10) = coroutineScope.launch {
        _state.value = UiState(loading = true)
        _state.value = UiState(loading = false, notes = getNotes(size))
    }

    data class UiState(
        val loading: Boolean = false,
        val notes: List<Note>? = null
    )
}

