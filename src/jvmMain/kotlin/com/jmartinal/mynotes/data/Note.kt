package com.jmartinal.mynotes.data

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val title: String,
    val description: String,
    val type: Type,
    val id: Long = NEW_NOTE_ID
) {
    companion object {
        const val NEW_NOTE_ID = -1L
    }

    enum class Type {
        TEXT,
        AUDIO
    }
}

