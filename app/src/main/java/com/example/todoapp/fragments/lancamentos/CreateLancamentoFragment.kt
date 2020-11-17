package com.example.todoapp.fragments.lancamentos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.fragments.categoria.CategoriaAdapter
import com.example.todoapp.fragments.categoria.CategoriaViewModel

import com.example.todoapp.model.Lancamento
import com.google.firebase.storage.FirebaseStorage

import kotlinx.android.synthetic.main.fragment_create_lancamento.*

class CreateLancamentoFragment : Fragment() {

    private lateinit var viewModel: LancamentoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =  inflater.inflate(R.layout.fragment_create_lancamento, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(LancamentoViewModel::class.java)

        viewModel.Lancamentos.observe(viewLifecycleOwner, Observer { t ->

            edit_LancamentoValor.setText(t.Valor.toString())

            view.findViewById<Button>(R.id.btn_LancamentoSubmit).setOnClickListener{

                val valor  = edit_LancamentoValor.text.toString()

                var newLancamento = Lancamento(

                    //docId not working as expected
                    docId = t.docId,
                    Valor = valor.toDouble()

                )

                viewModel.repository.saveLancamento(newLancamento)

                findNavController().navigateUp()

            }

        })



        return view

    }

}