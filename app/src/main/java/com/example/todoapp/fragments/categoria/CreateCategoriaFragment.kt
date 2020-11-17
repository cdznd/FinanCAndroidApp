package com.example.todoapp.fragments.categoria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.model.Categoria
import kotlinx.android.synthetic.main.fragment_create_categoria.*

class CreateCategoriaFragment : Fragment() {

    private lateinit var viewModel : CategoriaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_create_categoria, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(CategoriaViewModel::class.java)

        viewModel.Categorias.observe(viewLifecycleOwner, Observer { t ->

            edit_CategoriaName.setText(t.Name)

            view.findViewById<Button>(R.id.btn_CategoriaSubmit).setOnClickListener{

                val nome = edit_CategoriaName.text.toString()

                val newCategoria = Categoria(

                    docId = t.docId,
                    Name = nome

                )

                viewModel.repository.saveCategoria(newCategoria)

                findNavController().navigateUp()

            }

        })

        // Inflate the layout for this fragment
        return view

    }

}

