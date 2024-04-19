package com.jmartinal.mynotes.data.remote

import com.jmartinal.mynotes.data.Note
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

expect val NOTES_BASE_URL: String

object NotesRepository {

    // CREATE
    suspend fun save(note: Note) {
        notesClient.post(NOTES_BASE_URL) {
            setBody(note)
            contentType(ContentType.Application.Json)
        }
    }

    // READ
    suspend fun getAll(): List<Note> {
        val response = notesClient.request(NOTES_BASE_URL)
        return response.body()
    }

    suspend fun getById(noteId: Long): Note {
        val response = notesClient.request("$NOTES_BASE_URL/$noteId")
        return response.body()
    }

    // UPDATE
    suspend fun update(note: Note) {
        notesClient.put(NOTES_BASE_URL) {
            setBody(note)
            contentType(ContentType.Application.Json)
        }
    }

    // DELETE
    suspend fun delete(note: Note) {
        notesClient.delete("$NOTES_BASE_URL/${note.id}")
    }
}