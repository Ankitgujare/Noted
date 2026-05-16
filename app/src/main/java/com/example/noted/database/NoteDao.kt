package com.example.noted.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noted.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Note)

    @Delete
    suspend fun deleteNote(notes: Note)

    @Update
    suspend fun updateNote(notes: Note)

    @Query("SELECT * FROM NOTES")
    fun getAllNotes(): LiveData<List<Note>>

     @Query("SELECT * FROM NOTES WHERE noteTitle Like :query OR noteDescription Like :query")
     fun searchNotes(query: String?): LiveData<List<Note>>

}