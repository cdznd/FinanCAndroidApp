package com.example.todoapp.fragments.categoria

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.todoapp.R
import com.example.todoapp.model.Categoria

class CategoriaAdapter(

    private val activity : FragmentActivity,
    private val viewModel : CategoriaViewModel,
    private val categorias : List<Categoria>

) : RecyclerView.Adapter<CategoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_categoria, parent, false)

        return CategoriaViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {

        val categoria = categorias[position]

        holder.CategoriaNameView.text = categoria.Name

        holder.itemView.setOnClickListener{view ->

            viewModel.Categorias.value = categoria

            view.findNavController().navigate(R.id.action_categoriaFragment_to_createCategoriaFragment)

            true

        }

        holder.itemView.setOnLongClickListener{ view ->

            view?.let {

                AlertDialog.Builder(activity)
                    .setTitle("Excluir")
                    .setMessage("Tem certeza?")
                    .setPositiveButton("Sim"){

                            dialog, which -> viewModel.repository
                        .deleteCategoria(categoria.docId)
                    }
                    .setNegativeButton("Não",null)
                    .show()

            }

            true

        }

    }

    override fun getItemCount(): Int = categorias.size

}

