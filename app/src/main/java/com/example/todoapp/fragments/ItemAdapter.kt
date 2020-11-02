package com.example.todoapp.fragments

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.todoapp.R

import com.example.todoapp.model.Item

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ItemAdapter(

    //O adaptador pega o data e adapta para mostrar na recycler view
    private val items : List<Item>

) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ItemViewHolder {

        Log.i("Info","Inside OncreateViewHoloder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)

        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = items[position]

        holder.idView.text = item.id.toString()
        holder.contentView.text = item.Name

    }

    override fun getItemCount(): Int = items.size

}