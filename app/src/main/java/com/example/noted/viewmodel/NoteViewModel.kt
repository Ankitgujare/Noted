package com.example.noted.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noted.model.Note
import com.example.noted.reposatory.NoteReposatory
import kotlinx.coroutines.launch

class NoteViewModel(app: Application,private  val noteReposatory: NoteReposatory): AndroidViewModel(app) {



    fun addNote(note: Note){
        viewModelScope.launch {
             noteReposatory.addNotes(note)
        }
    }


    fun deleteNote(note: Note){
        viewModelScope.launch {
            noteReposatory.delete(note)
        }
    }


    fun updateNote(note: Note){
        viewModelScope.launch {
            noteReposatory.update(note)
        }
    }


    fun search(query:String?){
        noteReposatory.search(query)
    }

    fun getAllNotes()=noteReposatory.getAllNotes()

}