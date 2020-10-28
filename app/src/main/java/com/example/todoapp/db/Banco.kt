package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.model.Item

@Database(entities = [Item::class],version = 1,exportSchema = false)
abstract class Banco : RoomDatabase() {

    abstract fun ItemDAO() : ItemDAO

    companion object{

        @Volatile
        private var INSTANCE : Banco? = null

        fun get(conxt : Context) : Banco{

            var instance = INSTANCE

            if(instance == null){

                instance = Room.databaseBuilder(

                    conxt.applicationContext,
                    Banco::class.java,
                    "banco.db"

                ).build()

            }

            return instance!!

        }

    }

}