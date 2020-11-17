package com.example.todoapp.fragments.lancamentos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Lancamento
import com.example.todoapp.repository.LancamentoRepository

class LancamentoViewModel(app : Application) : AndroidViewModel(app) {

    //LiveData
    var Lancamentos = MutableLiveData<Lancamento>()

    var repository = LancamentoRepository()

    var LancamentoList = repository.listLancamento

}