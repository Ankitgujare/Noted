package com.example.noted

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.noted.database.NoteDatabase
import com.example.noted.reposatory.NoteReposatory
import com.example.noted.viewmodel.NoteViewModel
import com.example.noted.viewmodel.NoteViewmodelFactory
import com.example.notesroompractice.R

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        intiViewmodel()

    }

    private fun intiViewmodel(){
            val noteReposatory= NoteReposatory(NoteDatabase(this))
            val viewModelProviderFactory= NoteViewmodelFactory(application,
                noteReposatory = noteReposatory)

            noteViewModel= ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]

    }
}