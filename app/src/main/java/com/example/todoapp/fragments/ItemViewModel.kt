package com.example.todoapp.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.db.Banco
import com.example.todoapp.db.ItemDAO
import com.example.todoapp.model.Item
import kotlinx.coroutines.launch

class ItemViewModel(app : Application) : AndroidViewModel(app) {


    //private val items : MutableList<List<Item>>

    var items = MutableLiveData<Item>()

    var itemDAO = Banco.get(app).ItemDAO()

    //Return LiveData<List<Item>>
    var itemList = itemDAO.listAll()


    fun salvarItem(item : Item) = viewModelScope.launch{

        if(item.id == 0){

            itemDAO.inserir(item)

        }else{

            itemDAO.atualizar(item)

        }

    }

    fun deleteItem(id : Int) = viewModelScope.launch{

        itemDAO.delete(id)

    }


}

