package com.jmartinal.mynotes.data

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
    val notes = (1..size).map { position ->
        Note(
            title = "Title $position",
            description = "Description $position",
            type = if (position % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
        )
    }
    emit(notes)
}


