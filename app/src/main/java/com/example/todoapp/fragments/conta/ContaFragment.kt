package com.example.todoapp.fragments.conta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.model.Conta
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("infox","INside on createview")

        val view = inflater.inflate(R.layout.fragment_conta_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list_conta)

        val viewModel : ContaViewModel = ViewModelProvider(requireActivity()).get(ContaViewModel::class.java)

        viewModel.ContaList.observe(requireActivity(), Observer{ t ->

            with(recyclerView){

                adapter = ContaAdapter(requireActivity(),viewModel,t)

            }

        })

        val btnCreate = view.findViewById<FloatingActionButton>(R.id.btn_CreateConta)
        btnCreate.setOnClickListener(){

            Log.i("infox","Before floating button click")

            viewModel.Contas.value = Conta()
            findNavController().navigate(R.id.action_contaFragment_to_createContaFragment)

        }

        return view

    }

}