package com.example.todoapp.fragments.conta

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_conta.view.*

class ContaViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    //val ContaIdView : TextView = view.findViewById(R.id.Conta_id)
    val ContaNameView : TextView = view.findViewById(R.id.Conta_nome)
    val ContaCpfView : TextView = view.findViewById(R.id.Conta_cpf)

    //Profile picture
    val ContaPictureView : ImageView = view.findViewById(R.id.Conta_pic)

    //ButtonEdit
    val BtnEditConta : FloatingActionButton = view.findViewById(R.id.btn_EditConta)

    override  fun toString() : String {

        return super.toString() + " '" + ContaNameView.text + ""

    }

}