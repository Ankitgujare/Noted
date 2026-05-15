package com.example.noted.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noted.reposatory.NoteReposatory

class NoteViewmodelFactory (val app: Application,private  val noteReposatory: NoteReposatory): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app,noteReposatory) as T
    }
}