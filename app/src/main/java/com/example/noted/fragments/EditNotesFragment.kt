package com.example.noted.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noted.MainActivity
import com.example.noted.model.Note
import com.example.noted.viewmodel.NoteViewModel
import com.example.notesroompractice.R
import com.example.notesroompractice.databinding.FragmentEditNotesBinding

class EditNotesFragment : Fragment(), MenuProvider {

    private var editNotebindig:FragmentEditNotesBinding?=null
    private val binding get() =editNotebindig

    private lateinit var noteviewmodel: NoteViewModel

    private lateinit var currentNote: Note

    private val agrs: EditNotesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editNotebindig= FragmentEditNotesBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuhost: MenuHost=requireActivity()
        menuhost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteviewmodel=(activity as MainActivity).noteViewModel
        currentNote= agrs.note!!

        binding?.editNoteTitle?.setText(currentNote.noteTitle)
        binding?.editNoteDesc?.setText(currentNote.noteDescription)

        binding?.editNoteFab?.setOnClickListener {
           val noteTitle= binding?.editNoteTitle?.text.toString().trim()
           val noteDesc=binding?.editNoteDesc?.text.toString().trim()


           if (noteTitle.isNotEmpty()){
               val note= Note(
                   currentNote.id,
                   noteTitle,
                   noteDesc
               )

               noteviewmodel.updateNote(note)
               it.findNavController().popBackStack(R.id.homeFragment,false)
           }else{
               Toast.makeText(context,"Note Cannot be Empty", Toast.LENGTH_LONG).show()
           }
        }
    }


    private fun deleteNote(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Do You want to Delete this Note")
            setPositiveButton("Delete"){_,_->
                noteviewmodel.deleteNote(currentNote)
                Toast.makeText(context,"Note Deleted", Toast.LENGTH_LONG).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }

            setNegativeButton("Cancel",null)


        }.show()


    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
       return when(menuItem.itemId){

           R.id.deleteMenu->{
               deleteNote()
               true
           }
           else->false
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        editNotebindig=null

    }
}