import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

object AppState {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun loadNotes(coroutineScope: CoroutineScope, size: Int = 10) = coroutineScope.launch {
        _state.value = UiState(loading = true)
        getNotes(size).collect {
            _state.value = UiState(loading = false, notes = it)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val notes: List<Note>? = null
    )
}

