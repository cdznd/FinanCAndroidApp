package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Items")
data class Item (

    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var Name : String,
    var Description : String

)
{

    constructor() : this(0,"Nameless",String())

}
