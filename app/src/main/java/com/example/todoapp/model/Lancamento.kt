package com.example.todoapp.model

import java.util.*

data class Lancamento (

    var docId: String,
    var Valor : Double,
    var ContaNome : String,
    var CategoriaNome : String
    //TODO adicionar creationDate

)
{

    constructor() : this(String(),0.0,String(),String())

}