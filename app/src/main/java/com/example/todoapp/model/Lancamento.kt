package com.example.todoapp.model

data class Lancamento (

    var docId: String,
    var Valor : Double

    //TODO adicionar categoria e conta

)
{

    constructor() : this(String(),0.0)

}