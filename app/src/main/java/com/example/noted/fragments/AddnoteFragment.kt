package com.example.noted.fragments

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
import com.example.noted.MainActivity
import com.example.noted.model.Note
import com.example.noted.viewmodel.NoteViewModel
import com.example.notesroompractice.R
import com.example.notesroompractice.databinding.FragmentAddnoteBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AddnoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddnoteFragment : Fragment(R.layout.fragment_addnote), MenuProvider {
    private var addnoteBinding: FragmentAddnoteBinding?=null
    private val binding get() = addnoteBinding

    private lateinit var noteviewmodel: NoteViewModel

    private lateinit var addNoteView:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        addnoteBinding= FragmentAddnoteBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuhost: MenuHost=requireActivity()
        menuhost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)
        noteviewmodel=(activity as MainActivity).noteViewModel
        addNoteView=view
    }


    private fun saveNote(view:View){
        val noteTitle=binding?.addNoteTitle?.text.toString().trim()
        val noteDesc=binding?.addNoteDesc?.text.toString().trim()

        if (noteTitle.isNotEmpty()){
            val note= Note(0, noteTitle, noteDesc)
            noteviewmodel.addNote(note)

            Toast.makeText(addNoteView.context, "Note Saved Successfuly", Toast.LENGTH_LONG).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)

        }else{
            Toast.makeText(addNoteView.context, "Empty Note Found", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu->{
                saveNote(addNoteView)
                true
            }
            else->false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        addnoteBinding=null
    }
}