package com.example.todoapp.model

data class Conta (

    var docId : String,
    var Name : String,
    var Cpf : String,

    var Foto : String

)
{

    constructor() : this(String(),String(),String(), String())

}
