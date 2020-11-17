package com.example.todoapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Conta
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class ContaRepository {

    var listConta = MutableLiveData<MutableList<Conta>>()

    //Database instance
    private val db = FirebaseFirestore.getInstance()

    //Authentication instance
    val mAuth : FirebaseAuth = FirebaseAuth.getInstance();
    val user = mAuth.currentUser

    init{

        if(user == null){

            mAuth.signInAnonymously()

        }

        db.collection("Contas")
            .get()
            .addOnCompleteListener{ docs ->

                if(docs.isSuccessful){

                    var Contas = mutableListOf<Conta>()

                    docs.result?.forEach{ doc ->

                        val conta = doc.toObject(Conta::class.java)

                        if(conta != null){

                            conta.docId = doc.id
                            Contas.add(conta)

                        }

                    }

                    listConta.value = Contas

                }else{

                    listConta.value = mutableListOf()

                }

        }

        //READ
        db.collection("Contas")
            .addSnapshotListener{ snapshot, _ ->

                if(snapshot != null) {

                    var Contas = mutableListOf<Conta>()

                    snapshot.documents?.forEach{ doc ->

                        val conta = doc.toObject(Conta::class.java)

                        if(conta != null){

                            conta.docId = doc.id
                            Contas.add(conta)

                        }

                    }

                    listConta.value = Contas

                }

            }

    }

    //SAVE
    fun saveConta(conta : Conta){

        if(conta.docId.isBlank()){

            db.collection("Contas").document().set(conta)

        }else{

            db.collection("Contas")
                .document(conta.docId)
                .set(conta)

        }

    }

    //DELETE
    fun deleteConta(docId : String){

        db.collection("Contas")
            .document(docId)
            .delete()

    }

}