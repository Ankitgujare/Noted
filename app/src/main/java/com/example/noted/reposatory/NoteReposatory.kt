package com.example.noted.reposatory

import com.example.noted.database.NoteDatabase
import com.example.noted.model.Note

class NoteReposatory(val noteDatabase: NoteDatabase) {

    suspend fun addNotes(notes: Note)=noteDatabase.getNoteDao().insertNote(notes)
    suspend fun delete(notes: Note)=noteDatabase.getNoteDao().deleteNote(notes)
    suspend fun update(notes: Note)=noteDatabase.getNoteDao().updateNote(notes)
    fun search(query:String?)=noteDatabase.getNoteDao().searchNotes(query)
    fun getAllNotes()=noteDatabase.getNoteDao().getAllNotes()




}