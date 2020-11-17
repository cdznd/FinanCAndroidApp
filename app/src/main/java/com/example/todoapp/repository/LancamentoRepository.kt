package com.example.todoapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Lancamento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LancamentoRepository {

    var listLancamento = MutableLiveData<MutableList<Lancamento>>()

    //Database instance
    private val db = FirebaseFirestore.getInstance()

    //Authentication instance
    val mAuth : FirebaseAuth = FirebaseAuth.getInstance();
    val user = mAuth.currentUser

    init{

        if(user == null){

            mAuth.signInAnonymously()

        }

        db.collection("Lancamentos")
            .get()
            .addOnCompleteListener{ docs ->

                if(docs.isSuccessful){

                    var Lancamentos = mutableListOf<Lancamento>()

                    docs.result?.forEach{ doc ->

                        val lancamento = doc.toObject(Lancamento::class.java)

                        if(lancamento != null){

                            lancamento.docId = doc.id
                            Lancamentos.add(lancamento)

                        }

                    }

                    listLancamento.value = Lancamentos

                }else{

                    listLancamento.value = mutableListOf()

                }

            }

        //READ
        db.collection("Lancamentos")
            .addSnapshotListener{ snapshot, _ ->

                if(snapshot != null) {

                    var Lancamentos = mutableListOf<Lancamento>()

                    snapshot.documents?.forEach{ doc ->

                        val lancamento = doc.toObject(Lancamento::class.java)

                        if(lancamento != null){

                            lancamento.docId = doc.id
                            Lancamentos.add(lancamento)

                        }

                    }

                    listLancamento.value = Lancamentos

                }

            }

    }

    //SAVE
    fun saveLancamento(lancamento: Lancamento){

        if(lancamento.docId.isBlank()){

            db.collection("Lancamentos").document().set(lancamento)

        }else{

            db.collection("Lancamentos")
                .document(lancamento.docId)
                .set(lancamento)

        }

    }

    //DELETE
    fun deleteLancamento(docId : String){

        db.collection("Lancamentos")
            .document(docId)
            .delete()

    }


}