package com.example.todoapp.fragments

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val idView: TextView = view.findViewById(R.id.item_number)

    val contentView: TextView = view.findViewById(R.id.content)

    override fun toString(): String {

        return super.toString() + " '" + contentView.text + "'"

    }

}