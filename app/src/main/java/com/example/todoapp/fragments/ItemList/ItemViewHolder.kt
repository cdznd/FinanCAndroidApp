package com.example.todoapp.fragments.ItemList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //val idView: TextView = view.findViewById(R.id.item_number)
    //val contentView: TextView = view.findViewById(R.id.content)

    val idItem: TextView = view.findViewById(R.id.item_id)
    val nameItem: TextView = view.findViewById(R.id.item_name)
    val DescriptionItem: TextView = view.findViewById(R.id.item_description)

    override fun toString(): String {

        return super.toString() + " '" + idItem + "'" + nameItem + "'" + DescriptionItem

    }

}
