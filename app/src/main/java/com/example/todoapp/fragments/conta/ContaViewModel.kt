package com.example.todoapp.fragments.conta

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
//import com.example.todoapp.db.Banco
import com.example.todoapp.model.Categoria
import com.example.todoapp.model.Conta
import com.example.todoapp.repository.ContaRepository
import kotlinx.coroutines.launch

class ContaViewModel(app : Application) : AndroidViewModel(app) {

    //LiveData
    var Contas = MutableLiveData<Conta>()

    //var ContaDAO = Banco.get(app).ContaDAO()
    var repository = ContaRepository()

    var ContaList = repository.listConta

}