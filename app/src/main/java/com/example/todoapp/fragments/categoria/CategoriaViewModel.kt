package com.example.todoapp.fragments.categoria

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Categoria
import com.example.todoapp.repository.CategoriaRepository

class CategoriaViewModel(app : Application) : AndroidViewModel(app) {

    //LiveData
    var Categorias = MutableLiveData<Categoria>()

    var repository = CategoriaRepository()

    var CategoriaList = repository.listCategoria

}

