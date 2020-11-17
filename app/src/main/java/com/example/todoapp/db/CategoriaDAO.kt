package com.example.todoapp.db
/*
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.Categoria

@Dao
interface CategoriaDAO {

    @Query("SELECT * FROM Categorias")
    fun listAll() : LiveData<List<Categoria>>

    @Insert
    suspend fun inserir(categoria: Categoria)

    @Update
    suspend fun atualizar(categoria: Categoria)

    @Query("DELETE FROM Categorias WHERE id = (:id)")
    suspend fun delete(id: Int)

    @Query("DELETE FROM Categorias")
    suspend fun deleteAll()

}

 */