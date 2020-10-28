package com.example.todoapp.fragments.ItemList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.db.Banco
import com.example.todoapp.model.Item
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemViewModel(app : Application) : AndroidViewModel(app)  {

    //List of    modelsItem
    var items = MutableLiveData<Item>()

    //ItemDAO
    var ItemDAO = Banco.get(app).ItemDAO()

    //Lista com todos items
    var ItemList = ItemDAO.listAll()

    fun saveItem(item : Item) = viewModelScope.launch{

        if(item.id == 0){

            ItemDAO.inserir(item)

        }else{

            ItemDAO.atualizar(item)

        }

    }

    fun excluirItem(id : Int) = viewModelScope.launch {

        ItemDAO.delete(id)

    }

}