package com.example.todoapp.fragments.conta

import android.app.AlertDialog
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.todoapp.R

import com.example.todoapp.model.Conta
import com.google.firebase.storage.FirebaseStorage

class ContaAdapter(

    private val activity : FragmentActivity,
    private val viewModel : ContaViewModel,
    private val contas : List<Conta>

    ) : RecyclerView.Adapter<ContaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_conta, parent, false)

        return ContaViewHolder(view)

    }

    override fun onBindViewHolder(holder: ContaViewHolder, position: Int) {

        val conta = contas[position]

        holder.ContaNameView.text = conta.Name
        holder.ContaCpfView.text = conta.Cpf

        val picture : ImageView = holder.ContaPictureView

        //Firebase Instance
        val storage = FirebaseStorage.getInstance()
        //Storage Reference
        val storageReference = storage.reference

        storageReference.child(conta.Foto).downloadUrl.addOnSuccessListener { imageUrl ->

            if(imageUrl != null) {

                Glide.with(activity)
                    .load(imageUrl)
                    .into(picture)

            }else{

                Glide.with(activity)
                    .load(R.drawable.default_avatar)
                    .into(picture)

            }

        }

        storageReference.child(conta.Foto).downloadUrl.addOnFailureListener{

            Glide.with(activity)
                .load(R.drawable.default_avatar)
                .into(holder.ContaPictureView)

        }

        //CLICK LISTENER
        holder.itemView.setOnClickListener{ view ->

            viewModel.Contas.value = conta

            view.findNavController().navigate(R.id.action_contaFragment_to_createContaFragment)

            true

        }

        //LONG CLICK LISTENER
        holder.itemView.setOnLongClickListener{ view ->

            view?.let {

                AlertDialog.Builder(activity)
                    .setTitle("Atenção")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Sim"){

                            dialog, which -> viewModel.repository.deleteConta(conta.docId)

                    }
                    .setNegativeButton("Não",null)
                    .show()

            }

            true

        }

    }

    override fun getItemCount(): Int = contas.size

}