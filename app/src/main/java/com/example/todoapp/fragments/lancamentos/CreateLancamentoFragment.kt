package com.example.todoapp.fragments.lancamentos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.MainActivity
import com.example.todoapp.R
import com.example.todoapp.fragments.categoria.CategoriaAdapter
import com.example.todoapp.fragments.categoria.CategoriaViewModel
import com.example.todoapp.fragments.conta.ContaViewModel
import com.example.todoapp.model.Categoria

import com.example.todoapp.model.Lancamento
import com.google.firebase.storage.FirebaseStorage

import kotlinx.android.synthetic.main.fragment_create_lancamento.*
import org.w3c.dom.Text

class CreateLancamentoFragment : Fragment() {

    private lateinit var viewModel: LancamentoViewModel

    //Spinner arrays
    private lateinit var categoriaNames : ArrayList<String>

    private lateinit var contaNames : ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =  inflater.inflate(R.layout.fragment_create_lancamento, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(LancamentoViewModel::class.java)



        //FILL THE SPINNER WITH FIREBASE CATEGORIA DATA

        var categoriaviewModel = ViewModelProvider(requireActivity()).get(CategoriaViewModel::class.java)

        var CategoriaSpinner = view.findViewById<Spinner>(R.id.spinner_LancamentoFormCategoria)

        //Filling the spinner
        categoriaviewModel.CategoriaList.observe(viewLifecycleOwner, Observer { spinnerData ->

            categoriaNames = ArrayList()

            spinnerData.forEach{ x ->

                categoriaNames.add(x.Name)

            }

            val spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, categoriaNames)

            CategoriaSpinner.adapter = spinnerAdapter

        })



        //FILLING THE CONTA SPINNER WITH FIREBASE DATA AGAIN

        var contaViewModel : ContaViewModel = ViewModelProvider(requireActivity()).get(ContaViewModel::class.java)

        var ContaSpinner : Spinner = view.findViewById<Spinner>(R.id.spinner_LancamentoFormConta)

        contaViewModel.ContaList.observe(viewLifecycleOwner, Observer { spinnerData ->

            contaNames = ArrayList()

            spinnerData.forEach{ x ->

                contaNames.add(x.Name)

            }

            val spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item,contaNames)

            ContaSpinner.adapter = spinnerAdapter

        })


        //ON SELECT ***************

        //CATEGORIA ON SELECTED
        CategoriaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

                SpinnerContaResult.text = "Nada"

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                SpinnerCategoriaResult.text = categoriaNames[position]

            }

        }
        // =---------------------------------------------------

        //CONTA ON SELECTED
        ContaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

                SpinnerContaResult.text = "Nada"

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                SpinnerContaResult.text = contaNames[position]

            }

        }
        // =---------------------------------------------------

        viewModel.Lancamentos.observe(viewLifecycleOwner, Observer { t ->

            edit_LancamentoValor.setText(t.Valor.toString())

            //CategoriaSpinner.setSelection(categoriaSpinnerPosition)
            //ContaSpinner.setSelection(contaSpinnerPosition)

            //SUBMIT
            view.findViewById<Button>(R.id.btn_LancamentoSubmit).setOnClickListener{

                val valor  = edit_LancamentoValor.text.toString()

                var newLancamento = Lancamento(

                    docId = t.docId,
                    Valor = valor.toDouble(),
                    ContaNome = SpinnerContaResult.text.toString(),
                    CategoriaNome = SpinnerCategoriaResult.text.toString()

                )

                viewModel.repository.saveLancamento(newLancamento)

                findNavController().navigateUp()

            }

        })



        return view

    }

}