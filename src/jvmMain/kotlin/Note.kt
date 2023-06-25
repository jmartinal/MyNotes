import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

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

suspend fun getNotes(size: Int) = withContext(Dispatchers.IO) {
    delay(2000)
    (1..size).map { index ->
        Note(
            title = "Title $index",
            description = "Description $index",
            type = if (index % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
        )
    }
}


