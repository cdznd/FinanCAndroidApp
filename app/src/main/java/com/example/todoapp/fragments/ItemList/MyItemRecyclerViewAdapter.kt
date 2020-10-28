package com.example.todoapp.fragments.ItemList

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.todoapp.R

import com.example.todoapp.fragments.ItemList.dummy.DummyContent.DummyItem
import com.example.todoapp.model.Item

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(

    private val values: List<Item>

) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val itemx = values[position]

        holder.idItem.text = itemx.id.toString()
        holder.nameItem.text = itemx.Name
        holder.DescriptionItem.text = itemx.Description

    }

    override fun getItemCount(): Int = values.size

}