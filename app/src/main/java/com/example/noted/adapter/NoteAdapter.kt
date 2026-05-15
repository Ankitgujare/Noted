package com.example.noted.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.fragments.HomeFragmentDirections
import com.example.noted.model.Note
import com.example.notesroompractice.databinding.NoteLayoutBinding

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    class NoteViewHolder(val itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)


    //whenever we wanted to access any view from the Layout we will use itemBinding.viewName


    //creating a Callback
    private val differcallBack=object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {

            return oldItem.id==newItem.id &&
                    oldItem.noteDescription==newItem.noteDescription &&
                    oldItem.noteTitle==newItem.noteTitle

        }

        override fun areContentsTheSame(
            oldItem: Note,
            newItem: Note
        ): Boolean {
            return oldItem==newItem
        }
    }

    val differ= AsyncListDiffer(this,differcallBack)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {
        val currentNote=differ.currentList[position]

        holder.itemBinding.noteTitle.text=currentNote.noteTitle
        holder.itemBinding.noteDesc.text=currentNote.noteDescription


        holder.itemView.setOnClickListener {
            val dirrection= HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(currentNote)
            it.findNavController().navigate(dirrection)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}