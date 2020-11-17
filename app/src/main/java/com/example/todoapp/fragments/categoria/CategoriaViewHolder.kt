package com.example.todoapp.fragments.categoria

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class CategoriaViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val CategoriaNameView : TextView = view.findViewById(R.id.Categoria_nome)

    override fun toString() : String {

        return super.toString() + " '" + CategoriaNameView.text + ""

    }

}


