package com.example.todoapp.fragments.categoria

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.model.Categoria
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A fragment representing a list of Items.
 */
class CategoriaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_categoria_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list_categoria)

        val viewModel = ViewModelProvider(requireActivity()).get(CategoriaViewModel::class.java)

        viewModel.CategoriaList.observe(requireActivity(), Observer { t ->

            with(recyclerView){

                adapter = CategoriaAdapter(requireActivity(),viewModel,t)

            }

        })

        val btnCreate = view.findViewById<FloatingActionButton>(R.id.btn_CreateCategoria)
        btnCreate.setOnClickListener(){

            viewModel.Categorias.value = Categoria()
            findNavController().navigate(R.id.action_categoriaFragment_to_createCategoriaFragment)

        }

        return view

    }

}



