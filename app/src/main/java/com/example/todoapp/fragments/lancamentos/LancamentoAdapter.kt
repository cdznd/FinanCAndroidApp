package com.example.todoapp.fragments.lancamentos

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.todoapp.R

import com.example.todoapp.model.Lancamento

class LancamentoAdapter(
    private val activity : FragmentActivity,
    private val viewModel : LancamentoViewModel,
    private val lancamentos : List<Lancamento>
) : RecyclerView.Adapter<LancamentoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LancamentoViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_lancamento, parent, false)

        return LancamentoViewHolder(view)

    }

    override fun onBindViewHolder(holder: LancamentoViewHolder, position: Int) {

        val lancamento = lancamentos[position]

        holder.LancamentoValorView.text = lancamento.Valor.toString()

        //CLICK LISTENER
        holder.itemView.setOnClickListener{ view ->

            viewModel.Lancamentos.value = lancamento

            view.findNavController().navigate(R.id.action_lancamentoFragment_to_createLancamentoFragment)

            true

        }

        //LONG CLICK LISTENER
        holder.itemView.setOnLongClickListener{ view ->

            view?.let {

                AlertDialog.Builder(activity)
                    .setTitle("Atenção")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Sim"){

                            dialog, which -> viewModel.repository.deleteLancamento(lancamento.docId)

                    }
                    .setNegativeButton("Não",null)
                    .show()

            }

            true

        }

    }

    override fun getItemCount(): Int = lancamentos.size

}