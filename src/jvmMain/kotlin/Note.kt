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

fun getNotes(size: Int = 10, callback: (List<Note>) -> Unit) {
    Thread.sleep(2000)
    callback.invoke(
        (1..size).map { index ->
            Note(
                title = "Title $index",
                description = "Description $index",
                type = if (index % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
            )
        }
    )
}


