package com.example.todoapp.fragments.lancamentos

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

import com.example.todoapp.model.Lancamento
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LancamentoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_lancamento_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list_lancamento)

        val viewModel : LancamentoViewModel = ViewModelProvider(requireActivity()).get(LancamentoViewModel::class.java)

        viewModel.LancamentoList.observe(requireActivity(), Observer{ t ->

            with(recyclerView){

                adapter = LancamentoAdapter(requireActivity(),viewModel,t)

            }

        })

        val btnCreate = view.findViewById<FloatingActionButton>(R.id.btn_CreateLancamento)

        btnCreate.setOnClickListener(){

            viewModel.Lancamentos.value = Lancamento()
            findNavController().navigate(R.id.action_lancamentoFragment_to_createLancamentoFragment)

        }

        return view

    }

}