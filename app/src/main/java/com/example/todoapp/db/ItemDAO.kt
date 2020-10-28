package com.example.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.Item

@Dao
interface ItemDAO {

    @Query("SELECT * FROM Items")
    fun listAll() : LiveData<List<Item>>

    @Insert
    suspend fun inserir(item : Item)

    @Update
    suspend fun atualizar(item : Item)

    @Query("DELETE FROM Items WHERE id = (:id)")
    suspend fun delete(id: Int)

    @Query("DELETE FROM Items")
    suspend fun deleteAll()

}