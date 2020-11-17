package com.example.todoapp.fragments.conta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.model.Conta
import kotlinx.android.synthetic.main.fragment_create_conta.*

class CreateContaFragment : Fragment() {

    private lateinit var viewModel : ContaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =  inflater.inflate(R.layout.fragment_create_conta, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(ContaViewModel::class.java)

        viewModel.Contas.observe(viewLifecycleOwner, Observer { t ->

            edit_ContaNome.setText(t.Name)
            edit_ContaCPF.setText(t.Cpf)
            edit_ContaFormPicture.setText(t.Foto)

            view.findViewById<Button>(R.id.btn_ContaSubmit).setOnClickListener{

                val nome : String = edit_ContaNome.text.toString()
                val cpf : String = edit_ContaCPF.text.toString()
                val foto : String = edit_ContaFormPicture.text.toString()

                var newConta = Conta(

                    //docId not working as expected
                    docId = t.docId,
                    Name = nome,
                    Cpf = cpf,
                    Foto = foto

                )

                viewModel.repository.saveConta(newConta)

                findNavController().navigateUp()

            }

        })

        return view

    }

}