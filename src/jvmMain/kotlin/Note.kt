import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class Note(
    val title: String,
    val description: String,
    val type: Type
) {

    enum class Type {
        TEXT,
        AUDIO
    }
}

fun getNotes(size: Int): Flow<List<Note>> = flow {
    delay(2000)
    var notes = emptyList<Note>()
    (1..size).forEach { position ->
        notes = notes + Note(
            title = "Title $position",
            description = "Description $position",
            type = if (position % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
        )
        emit(notes)
        delay(500)
    }
}


