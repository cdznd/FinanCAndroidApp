package com.example.todoapp.fragments.lancamentos

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class LancamentoViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val LancamentoValorView : TextView = view.findViewById(R.id.Lancamento_valor)
    val LancamentoCategoriaView : TextView = view.findViewById(R.id.Lancamento_Categoria)
    val LancamentoContaView : TextView = view.findViewById(R.id.Lancamento_Conta)

    override fun toString() : String {

        return super.toString() + " '" + LancamentoValorView.text + ""

    }

}