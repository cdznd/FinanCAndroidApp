package com.example.todoapp.fragments.conta

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todoapp.R
import com.example.todoapp.fragments.categoria.CategoriaViewModel
import com.example.todoapp.fragments.lancamentos.LancamentoAdapter
import com.example.todoapp.fragments.lancamentos.LancamentoViewModel
import com.example.todoapp.model.Conta
import com.example.todoapp.model.Lancamento
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_conta_info.*

class ContaInfoFragment : Fragment() {

    private lateinit var viewModel : ContaViewModel
    private lateinit var lancamentoViewModel : LancamentoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_conta_info, container, false)

        val lancamentoRecycler = view.findViewById<RecyclerView>(R.id.list_LancamentosConta)
        lancamentoRecycler.layoutManager = LinearLayoutManager(requireContext())

        val btnDelete = view.findViewById<FloatingActionButton>(R.id.btn_ContaInfoDelete)

        val btnEdit = view.findViewById<FloatingActionButton>(R.id.btn_ContaInfoEdit)

        val btnAddLancamento = view.findViewById<FloatingActionButton>(R.id.btn_addLancamento)

        viewModel = ViewModelProvider(requireActivity()).get(ContaViewModel::class.java)

        lancamentoViewModel = ViewModelProvider(requireActivity()).get(LancamentoViewModel::class.java)


        //Contas observe
        viewModel.Contas.observe(viewLifecycleOwner, Observer { t ->

            text_nome.setText(t.Name)
            text_cpf.setText(t.Cpf)
            text_id.setText(t.docId)

            val contaPic : ImageView = view.findViewById(R.id.img_picture)

            //Firebase Instance
            val storage = FirebaseStorage.getInstance()
            //Storage Reference
            val storageReference = storage.reference

            //Setting pic
            storageReference.child(t.Foto).downloadUrl.addOnSuccessListener { imageUrl ->

                if(imageUrl != null) {

                    Glide.with(this)
                        .load(imageUrl)
                        .into(contaPic)

                }else{

                    Glide.with(this)
                        .load(R.drawable.default_avatar)
                        .into(contaPic)

                }

            }

            storageReference.child(t.Foto).downloadUrl.addOnFailureListener{

                Glide.with(this)
                    .load(R.drawable.default_avatar)
                    .into(contaPic)

            }

            //Delete
            btnEdit.setOnClickListener{

                findNavController().navigate(R.id.action_contaInfoFragment_to_createContaFragment)

            }

            //Update
            btnDelete.setOnClickListener{

                view?.let {

                    AlertDialog.Builder(activity)
                        .setTitle("Atenção")
                        .setMessage("Confirmar")
                        .setPositiveButton("Sim"){

                                dialog, which -> viewModel.repository.deleteConta(t.docId)
                                findNavController().navigateUp()

                        }
                        .setNegativeButton("Não",null)
                        .show()

                }

            }

            //Lancamento Observer on LancamentoList
            lancamentoViewModel.LancamentoList.observe(viewLifecycleOwner, Observer { l ->

                //Array com todos lancamentos com o mesmo nome de conta
                var lancamentosConta : ArrayList<Lancamento> = ArrayList()
                var totalGasto : Double = 0.0

                l.forEach{ x ->

                    if(x.ContaNome == t.Name){

                        lancamentosConta.add(x)
                        totalGasto += x.Valor

                    }

                }

                text_NumeroLancamentos.setText("Numero de lancamentos = " + lancamentosConta.size)
                text_TotalGasto.setText("Total gasto pela conta = " + totalGasto)

                //Setting listView
                with(lancamentoRecycler){

                    adapter = LancamentoAdapter(requireActivity(),lancamentoViewModel,lancamentosConta)

                }

            })

        })

        btnAddLancamento.setOnClickListener{

            lancamentoViewModel.Lancamentos.value = Lancamento()
            findNavController().navigate(R.id.action_contaInfoFragment_to_createLancamentoFragment)

        }

        return view

    }

}