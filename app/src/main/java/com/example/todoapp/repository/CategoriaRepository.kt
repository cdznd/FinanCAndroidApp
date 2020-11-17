package com.example.todoapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Categoria
import com.example.todoapp.model.Conta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CategoriaRepository {

    var listCategoria = MutableLiveData<MutableList<Categoria>>()

    //Database instance
    private val db = FirebaseFirestore.getInstance()

    //Authentication instance
    val mAuth : FirebaseAuth = FirebaseAuth.getInstance();
    val user = mAuth.currentUser

    init {

        if (user == null) {

            mAuth.signInAnonymously()

        }

        db.collection("Categorias")
            .get()
            .addOnCompleteListener { docs ->

                if (docs.isSuccessful) {

                    var Categorias = mutableListOf<Categoria>()

                    docs.result?.forEach { doc ->

                        val categoria = doc.toObject(Categoria::class.java)

                        if (categoria != null) {

                            categoria.docId = doc.id
                            Categorias.add(categoria)

                        }

                    }

                    listCategoria.value = Categorias

                } else {

                    listCategoria.value = mutableListOf()

                }

            }

        db.collection("Categorias")
            .get()
            .addOnCompleteListener{ docs ->

                if(docs.isSuccessful){

                    var Categorias = mutableListOf<Categoria>()

                    docs.result?.forEach{ doc ->

                        val categoria = doc.toObject(Categoria::class.java)

                        if (categoria != null) {

                            categoria.docId = doc.id
                            Categorias.add(categoria)

                        }

                    }

                    listCategoria.value = Categorias

                }else{

                    listCategoria.value = mutableListOf()

                }

            }

        //READ
        db.collection("Categorias")
            .addSnapshotListener{ snapshot, _ ->

                if(snapshot != null){

                    var Categorias = mutableListOf<Categoria>()

                    snapshot.documents?.forEach{ doc ->

                        val categoria = doc.toObject(Categoria::class.java)

                        if(categoria != null){

                            categoria.docId = doc.id
                            Categorias.add(categoria)

                        }

                    }

                    listCategoria.value = Categorias

                }

            }

    }

    //SAVE CATEGORIA
    fun saveCategoria(categoria: Categoria){

        if(categoria.docId.isBlank()){

            db.collection("Categorias").document().set(categoria)

        }else{

            db.collection("Categorias")
                .document(categoria.docId)
                .set(categoria)

        }

    }

    //DELETE CATEGORIA
    fun deleteCategoria(docId : String){

        db.collection("Categorias")
            .document(docId)
            .delete()

    }






}