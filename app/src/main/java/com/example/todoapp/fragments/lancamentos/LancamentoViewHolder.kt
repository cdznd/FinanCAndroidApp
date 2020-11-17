package com.example.todoapp.fragments.lancamentos

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class LancamentoViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val LancamentoValorView : TextView = view.findViewById(R.id.Lancamento_valor)

    override fun toString() : String {

        return super.toString() + " '" + LancamentoValorView.text + ""

    }

}